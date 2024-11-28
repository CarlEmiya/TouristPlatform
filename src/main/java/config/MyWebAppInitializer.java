package config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	// 指定Spring配置类有哪些，加载这些配置类形成根容器，根容器管理程序的基础bean（数据源、Dao、Service等）
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringConfig.class };
	}
	// 指定Spring MVC配置类有哪些，加载这些配置类形成Servlet上下文容器，上下文容器管理Web层相关的bean，包括控制器、视图解析器等
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringMVCConfig.class };
	}
	// 指定DispatcherServlet拦截的请求的url，/表示除了*.jsp之外的所有请求
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// 在该方法中，注册要使用的过滤器
	@Override
	protected Filter[] getServletFilters() {

		// 实例化CharacterEncodingFilter过滤器，指定要使用的编码和其他配置
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		// 设置使用的字符集编码
		filter.setEncoding("UTF-8");
		// 强制请求使用指定的编码
		filter.setForceRequestEncoding(true);
		// 强制响应使用指定的编码
		filter.setForceResponseEncoding(true);

		// 将过滤器放入数据中，相当于注册，这样过滤器才会被应用
		return new Filter[] { filter };


	}
}
