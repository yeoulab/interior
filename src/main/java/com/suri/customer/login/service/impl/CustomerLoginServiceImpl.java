package com.suri.customer.login.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suri.customer.login.controller.CustomerLoginController;
import com.suri.customer.login.model.Customer;
import com.suri.customer.login.repository.CustomerRepository;
import com.suri.customer.login.service.CustomerLoginService;
import com.suri.customer.util.CommonUtil;

@Service("CustomerLoginService")
public class CustomerLoginServiceImpl implements CustomerLoginService {
	@Autowired
	CustomerRepository customerRepository;
	
	static Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);
		
	public Customer login(String email, String pwd) {
		Customer customer = customerRepository.findByEmail(email);

		if( customer == null ) {
    		logger.info("Null Pointer Exception 발생");
    		throw new NullPointerException("해당 계정 없음");
    	}
    	
    	String memberSalt = customer.getSalt();
    	String saltPwd = CommonUtil.getEncrypt(customer.getPwd(), memberSalt);
    	
    	if( customer.getPwd().equals(saltPwd) ) {
    		logger.info("login 성공");
    	}
		
		return customer;
	}
	
	public void createCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);;
	}
}

