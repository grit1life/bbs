package com.cafe24.louw0.interceptor;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	
	public static Path SERVER_REAL_PATH;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String serverName = request.getServerName();
		
		logger.info("CommonInterceptor==========================");
		logger.info("ACCESS INFO ======================= START ");
		logger.info("Port           :::::{} ", request.getLocalPort());
		logger.info("serverName     :::::{} ", serverName);
		logger.info("getMethod      :::::{} ", request.getMethod());
		logger.info("getRequestURI  :::::{} ", request.getRequestURI());
		logger.info("ACCESS INFO ======================= END");
		
		if(SERVER_REAL_PATH == null) {			
			String serverRealPath = request.getSession().getServletContext().getRealPath("/");
			if(serverName.indexOf("localhost") > -1 || serverName.indexOf("127.0.0.1") > -1) {			
				serverRealPath = serverRealPath.replace("\\target\\m2e-wtp\\web-resources", "");
				serverRealPath = serverRealPath + "\\src\\main\\webapp";
			}
			
			SERVER_REAL_PATH = Paths.get(serverRealPath);
		}	
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("CommonInterceptor =====================");
		super.postHandle(request, response, handler, modelAndView);
	}

}
