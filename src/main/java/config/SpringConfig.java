package config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
//@PropertySource("classpath:com.qdu.config.dbconfig.properties")
@PropertySource("classpath:com/qdu/config/dbconfig.properties")
@Configuration
@ComponentScan(basePackages = {"com.qdu.service"})
@MapperScan("com.qdu.mapper")
public class SpringConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        dataSource.setInitialSize(Integer.parseInt(env.getProperty("initialSize")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("maxActive")));
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("maxWait")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("minIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("minEvictableIdleTimeMillis")));

        dataSource.setValidationQuery(env.getProperty("validationQuery"));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("minEvictableIdleTimeMillis")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("testOnReturn")));

        return dataSource;
    }
    // 集成spring+Mybatis，如果希望使用PageHelper插件实现分页功能
    // 需要将PageHelper插件提供的PageInterceptor注册为spring管理的bean
    // 注册为spring管理的bean，默认，该bean的作用域/范围是单例，也就是始终返回同一个bean实例
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setTypeAliasesPackage("com.qdu.entity");
        factoryBean.setDataSource(dataSource()); // 调用上面的方法获取数据源注入
        // 调用setPlugins注入要使用的插件，可以注入任意多个插件
        factoryBean.setPlugins(pageInterceptor());
        return factoryBean.getObject(); // 返回创建的SqlSessionFactory对象
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 设置mybatis-config.xml的位置
        Resource configResource = new ClassPathResource("com/qdu/config/mybatis-config.xml");
        try {
            sqlSessionFactoryBean.setConfigLocation(configResource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setTypeHandlers(new StringToJsonObject(gson()),new StringToJsonArray(gson()));
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        // 需要注入事务管理器依赖的数据源
        return new DataSourceTransactionManager(dataSource());
    }


}
