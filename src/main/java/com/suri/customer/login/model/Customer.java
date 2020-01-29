package com.suri.customer.login.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "pwd")
public class Customer {
	
	public Customer() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uid;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false, unique = true)
	private String phone;
	
	@Builder
	public Customer(String email, String pwd, String phone) {
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
	}
}
