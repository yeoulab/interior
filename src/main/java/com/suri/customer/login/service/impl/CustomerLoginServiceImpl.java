package com.suri.customer.login.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suri.customer.login.model.Customer;
import com.suri.customer.login.repository.CustomerRepository;
import com.suri.customer.login.service.CustomerLoginService;

@Service("CustomerLoginService")
public class CustomerLoginServiceImpl implements CustomerLoginService {
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> login(String email, String pwd) {
		return customerRepository.findCustomerByEmailAndPassword(email, pwd);
	}
	
	public void createCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);;
	}
}
