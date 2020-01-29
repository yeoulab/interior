package com.suri.customer.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.suri.customer.login.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	// trouble-shooting
	// 1. Queyr Annotation 을 사용해서 Repository Method 를 만들 때 오류가 많이 발생했다.
	//  -> Table 명도 대소문자가 중요하다
	
	@Query(value = "select t from Customer t where t.email = :email and t.pwd = :pwd")
	List<Customer> findCustomerByEmailAndPassword(@Param("email") String email, @Param("pwd") String pwd);
	
//	Collection<Customer> findBy;
}

