package com.zfx.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan("com.zfx")
public class WebConfig {

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver() ;
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		//使用spring的JSTL标签库渲染视图
		viewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		return viewResolver ;
	}
	
	/**
	 * 会加载根目录下message.properties文件，供页面spring标签展示数据使用
	* @Description: TODO  
	* @return
	* @return MessageSource
	* @throws  
	* @author: zhaofuxiang
	* @date: 2019年6月25日 上午10:12:11
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rrbms = new ResourceBundleMessageSource();
		rrbms.setBasename("message");
		return rrbms ;
	}
}
