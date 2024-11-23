package config;

import org.apache.ibatis.plugin.Interceptor;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@Configuration
@ComponentScan("com.qdu")
@MapperScan("com.qdu.mapper") // 指定Mapper接口扫描路径
public class MyBatisConfig {

    @Bean
    public PooledDataSource dataSource() {
        // 读取数据库配置信息
        Properties dbProperties = new Properties();
        try {
            dbProperties.load(new ClassPathResource("com/qdu/config/dbconfig.properties").getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load dbconfig.properties", e);
        }

        // 配置数据源
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(dbProperties.getProperty("jdbc.driver"));
        dataSource.setUrl(dbProperties.getProperty("jdbc.url"));
        dataSource.setUsername(dbProperties.getProperty("jdbc.username"));
        dataSource.setPassword(dbProperties.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(PooledDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // 配置 MyBatis 的基础配置
        Configuration configuration = new Configuration();
        configuration.getTypeAliasRegistry().registerAliases("com.qdu.entity"); // 别名包
        configuration.setMapUnderscoreToCamelCase(true); // 启用驼峰命名映射

        // 添加分页插件
        Interceptor pageInterceptor = new org.mybatis.plugin.page.PageInterceptor();
        Properties pageProps = new Properties();
        pageProps.setProperty("pageSize", "10");
        pageProps.setProperty("reasonable", "true");
        pageInterceptor.setProperties(pageProps);
        configuration.addInterceptor(pageInterceptor);

        factoryBean.setConfiguration(configuration);

        // 配置 Mapper XML 文件位置
        factoryBean.setMapperLocations(new ClassPathResource("com/qdu/mapper"));

        return factoryBean.getObject();
    }
}
