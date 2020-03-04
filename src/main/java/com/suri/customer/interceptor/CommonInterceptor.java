package com.suri.customer.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.suri.customer.configuration.CustomHttpServletRequestWrapper;
import com.suri.customer.login.model.Customer;

@Component
public class CommonInterceptor implements HandlerInterceptor{
	
	Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("Interceptor > preHandle");
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		logger.info("uri : " +request.getRequestURI());
		logger.info("session id : " + session.getId());
		logger.info("session created time : " + session.getCreationTime());
	
		if( customer == null ) {
			logger.info("customer : " + customer);
			
			throw new NullPointerException("Session 이 만료됐습니다");
			
		}else {
			return true;
		}
    }
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		logger.info("Interceptor > postHandle");
	}
}

