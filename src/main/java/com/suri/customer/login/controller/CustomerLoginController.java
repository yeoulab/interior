package com.suri.customer.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suri.customer.login.model.Customer;
import com.suri.customer.login.repository.CustomerRepository;
import com.suri.customer.login.service.CustomerLoginService;
import com.suri.customer.util.CommonUtil;

@RestController
@RequestMapping(value="/customer")
public class CustomerLoginController {
	
	static Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerLoginService customerLoginService;
	
	@GetMapping("/test")
	public String customerTestUrl() {
		/* 서버 테스트용 */
		return "Server starts";
	}
	@RequestMapping("/get")
	public String getMessage() {
		/* 서버 and DB 테스트용 */
		List<Customer> customerList = customerRepository.findAll();
		
		logger.info("getMessage");
		return customerList.get(0).toString();
	}	
	
	@PostMapping("/register")
	public void registerCustomer(@RequestBody Customer cs) throws IOException {
	    	
		String pwd, newPwd, salt;
	    salt = CommonUtil.generateSalt(); 
	    pwd = cs.getPwd(); 
	    newPwd = CommonUtil.getEncrypt(pwd, salt);
	    
	    Customer customer = Customer.builder().name(cs.getName())
                .phone(cs.getPhone())
                .pwd(newPwd)
                .salt(salt)
                .email(cs.getEmail())
                .build();
	    
	    customerLoginService.createCustomer(customer);	   		
	}	
	
	@PostMapping("/login")
	public Customer login(HttpServletRequest request, HttpServletResponse response, @RequestBody Customer customer) 
	throws Exception{
				
		Customer customerResult = customerLoginService.login(customer.getEmail(), customer.getPwd());
		
		if( customerResult != null ) {
			customerResult.setPwd("");
			customerResult.setSalt("");
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("customer", customerResult);
		}else {
			throw new Exception("고객정보 없음");
		}
		
		return customerResult;
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Customer Logout Controller called");
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
	}
}

