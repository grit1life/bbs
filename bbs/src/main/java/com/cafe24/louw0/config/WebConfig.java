package com.cafe24.louw0.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.louw0.interceptor.LoginInterceptor;

@Configuration
public class WebConfig /* implements WebMvcConfigurer */ {

	@Autowired
	private LoginInterceptor loginInterceptor;
	
	//@Override
	//public void addInterceptors(InterceptorRegistry registry) {
	//	registry.addInterceptor(loginInterceptor)
	//			.addPathPatterns("/**").ex;	
	//}
	
}
