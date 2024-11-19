package config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringMVCConfig.class };
	}

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
