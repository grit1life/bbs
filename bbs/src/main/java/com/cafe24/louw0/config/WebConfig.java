package com.cafe24.louw0.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.louw0.interceptor.CommonInterceptor;
import com.cafe24.louw0.interceptor.LoginInterceptor;

@Configuration
@ComponentScan("com.cafe24.louw0")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private CommonInterceptor commonInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**");	
		
		//registry.addInterceptor(loginInterceptor)
		//		.addPathPatterns("/boardList");	
	}
	
}
