package com.suri.customer.login.service;

import java.util.List;

import com.suri.customer.login.model.Customer;

public interface CustomerLoginService {
	public List<Customer> login(String email, String pwd);
	public void createCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	//PasswordEncoder passwordEncoder();
}
