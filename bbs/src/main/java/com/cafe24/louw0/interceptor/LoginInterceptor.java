package com.cafe24.louw0.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String sId = (String) session.getAttribute("sId");
		if(sId==null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					if("sId".equals(cookies[i].getName())) {
						session.setAttribute("sId", cookies[i].getValue());
					}else if("nickname".equals(cookies[i].getName())) {
						session.setAttribute("nickname", cookies[i].getValue());
					}else if("level".equals(cookies[i].getName())) {
						session.setAttribute("level", cookies[i].getValue());
					}
				}
			}else {
				response.sendRedirect(".");
			}
		}
		return super.preHandle(request, response, handler);
	}
}
