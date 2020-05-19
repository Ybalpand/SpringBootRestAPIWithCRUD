package com.app.springbootrestapi.service;

import java.util.List;


import com.app.springbootrestapi.entity.Customer;
import com.app.springbootrestapi.entity.Document;
import com.app.springbootrestapi.entity.Gender;

public interface CustomerService {

	List<Customer> getAllCustomerList();
	
	Customer getCustomerById(int customerId);
	
	Customer createCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(int customerId);
	
	List<Document> getAllDocumentList();

	List<Customer> getCustomerbyGender(String gender);
	
	
	
}
