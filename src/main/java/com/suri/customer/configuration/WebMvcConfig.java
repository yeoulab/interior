package com.suri.customer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.suri.customer.interceptor.CommonInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(commonInterceptor)
		                    .excludePathPatterns("/**");
                                    /*
				    .excludePathPatterns("/login")
				    .excludePathPatterns("/register");
		                    .addPathPatterns("/*")*/
	}
	
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
		            .allowedOrigins("*")
		            .allowedMethods("POST", "GET")
		            .allowCredentials(true)
		            .allowedHeaders("*")
		            .maxAge(3600);
	}
}

