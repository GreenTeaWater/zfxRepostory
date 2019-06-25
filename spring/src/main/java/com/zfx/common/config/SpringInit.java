package com.zfx.common.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
   在Servlet 3.0环境中，容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类，
   如果能发现的话，就会用它来配置Servlet容器
   
 Spring提供了这个接口的实现，名为SpringServletContainerInitializer，这个类反过来又会查找实现了WebApplicationInitializer的类并将
   配置的任务交给它们来完成。Spring 3.2引入了一个便利的WebApplicationInitializer基础实现，
   也就是AbstractAnnotationConfigDispatcherServletInitializer。
   如果类继承了AbstractAnnotationConfigDispatcherServlet-Initializer（同时也就实WebApplicationInitializer），
   因此当部署到Servlet 3.0容器中的时候，容器会自动发现它，并用它来配置Servlet上下文。
 */
public class SpringInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	/**
	 * Spring上下文配置类
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	/**
	 * springMVC上下文配置类
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}

	/**
	  *      将一个或多个路径映射到DispatcherServlet上 
	 *  "/"这表示它会是应用的默认Servlet
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
