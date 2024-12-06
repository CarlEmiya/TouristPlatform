package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
//@PropertySource("classpath:com.travel.config.dbconfig.properties")
@PropertySource("classpath:com/travel/config/dbconfig.properties")
@Configuration
@MapperScan(basePackages = {"com.travel.mapper"})
@ComponentScan(basePackages = {"com.travel.service"})
@MapperScan("com.travel.mapper")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DataSource dataSource(
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password
    ) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 设置mybatis-config.xml的位置
        Resource configResource = new ClassPathResource("com/travel/config/mybatis-config.xml");
        try {
            sqlSessionFactoryBean.setConfigLocation(configResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;



//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        factory.setTypeAliasesPackage("com.qdu.entity");
//        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        // 实例事务管理器，指定以来的数据源
//        return new DataSourceTransactionManager(dataSource());
//    }


}
