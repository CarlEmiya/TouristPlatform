package config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.travel.controller"})
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        // 可以针对上传的文件指定一个虚拟路径，用于通过虚拟路径访问真实路径下的资源
        // file: 开头表示要使用的位置来自文件系统，比如C盘或D盘中某个位置
        // /uploaded/**设置的是访问上传的图片的模式，也就是虚拟路径，这个可以随便写
        registry.addResourceHandler("/uploaded/**").addResourceLocations("file:D:\\A课本\\学习\\大三下Java项目\\TouristPlatform\\src\\main\\webapp\\static\\uploaded\\");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/testComment").setViewName("testComment");
        registry.addViewController("/comment").setViewName("testComment");
        registry.addViewController("/userInfo").setViewName("UserInfo");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return viewResolver;
    }

    // 如果希望在thymeleaf页面上读取属性文件(.properties)中的属性
    // 得配置属性文件所在的位置和名称
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 如果只有一个属性文件，可以调用setBasename()来设置文件位置和名称
        // 如果有多个属性文件，可以调用setBasenames()来设置文件位置和名称
        // 指定文件信息，需要给出 包名.文件名，不需要写扩展名，也不需要写语言代码和国家地区代码
        messageSource.setBasename("properties.messages");
        return messageSource;
    }
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    // 在spring mvc程序中实现文件上传，只需要配置一个MultipartResolver组件
    // 这样spring mvc会帮我们做一些封装，在控制器中写代码获取上传文件信息就很简单
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        // 创建CommonsMultipartResolver对象，并且进行需要的配置
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // 这里的编码配置主要是为了解决可能出现的请求参数乱码问题，比如，包含中文
        resolver.setDefaultEncoding("UTF-8");
        // 可以限定上传文件的总大小
        resolver.setMaxUploadSize(50 * 1024 * 1024);
        // 可以限定每个上传的文件的大小
        // resolver.setMaxUploadSizePerFile();
        return resolver;
    }



}