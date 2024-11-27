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

import javax.sql.DataSource;
//@PropertySource("classpath:com.qdu.config.dbconfig.properties")
@PropertySource("classpath:com/qdu/config/dbconfig.properties")
@Configuration
@ComponentScan(basePackages = {"com.qdu.service"})
@MapperScan("com.qdu.mapper")
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

        // 设置连接池参数
//        dataSource.setInitialSize(5);
//        dataSource.setMinIdle(5);
//        dataSource.setMaxActive(20);
//        dataSource.setMaxWait(60000);
//        dataSource.setTimeBetweenEvictionRunsMillis(60000);
//        dataSource.setMinEvictableIdleTimeMillis(300000);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestOnReturn(false);

        return dataSource;
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

        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setTypeHandlers(new StringToJsonObject(gson()),new StringToJsonArray(gson()));
        return sqlSessionFactoryBean;
    }


}
