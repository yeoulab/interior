package com.suri.customer.login.controller;

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

@RestController
@RequestMapping("/customer")
public class CustomerLoginController {
	
	static Logger logger = LoggerFactory.getLogger(CustomerLoginController.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerLoginService customerLoginService;
	
	@GetMapping("/test")
	public String customerTestUrl() {
		return "Server starts";
	}
	
	@PostMapping("/register")
	public void registerCustomer(HttpServletRequest request) {
		
	}	
	
	@PostMapping("/login")
	//public Customer login(HttpServletRequest request, HttpServletResponse response) {
	public Customer login(@RequestBody Customer customer) {
		
		// Session 을 받아서 넘기는 부분은 Controller 가 아니라 filter 나 interceptor 에 놓아야 한다.
//		logger.info("email : " + request.getAttribute("email"));
		logger.info("email: " + customer.getEmail());
		// httpSession 안에 세션정보가 있으면
		// 고객정보를 확인한 후 return한다
		//HttpSession httpSession = request.getSession();
		//if( !httpSession.getId().isEmpty() ) {
		//	customer = new Customer();
		//	customer.setEmail((String)httpSession.getAttribute("email"));
		//	customer.setPhone((String)httpSession.getAttribute("phone"));
		//	return customer;
		//}
		
		// httpSession 안에 정보가 없는 경우, body 에 있는 값을 받아 login 한다.
		//String email = (String)request.getAttribute("email");
		//String pwd = (String)request.getAttribute("pwd");
		
		List<Customer> listCustomer = customerLoginService.login(customer.getEmail(), customer.getPwd());
		
		if( listCustomer != null ) {
			//HttpSession httpSession = request.getSession();
			//httpSession.setAttribute("customer", listCustomer.get(0));
		}else {
			// error 처리
		}
		
		return listCustomer.get(0);
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Customer Logout Controller called");
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
	}

	@RequestMapping("/get")
	public String getMessage() {
		Customer customer = new Customer("shinmaeng@naver.com", "1111","01024393039");
		customerRepository.save(customer);
		
		List<Customer> customerList = customerRepository.findAll();
		
		logger.info("getMessage");
		return customerList.get(0).toString();
	}
	

}
