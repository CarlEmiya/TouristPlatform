package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.qdu.service","com.qdu.mapper"})
public class SpringConfig {
	
}
