package com.suri.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CustomerApplication extends SpringBootServletInitializer {

	static Logger logger = LoggerFactory.getLogger(CustomerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
		// 왜 이 로그들은 안 뜨는거지??
		logger.info("Customer Application Start");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CustomerApplication.class);
	}

}
