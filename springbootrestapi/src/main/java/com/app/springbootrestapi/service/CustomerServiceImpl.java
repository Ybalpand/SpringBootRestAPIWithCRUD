package com.app.springbootrestapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.app.springbootrestapi.dao.CustomerDAO;
import com.app.springbootrestapi.dao.DocumentDAO;
import com.app.springbootrestapi.entity.Customer;
import com.app.springbootrestapi.entity.Document;
import com.app.springbootrestapi.exception.CustomerNotFoundException;
import com.app.springbootrestapi.entity.Gender;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	DocumentDAO documentDAO;

	@Override
	public List<Document> getAllDocumentList() {
		System.out.println("Document list In service layer");
		return documentDAO.findAll();
	}

	@Override
	public List<Customer> getAllCustomerList() {
		System.out.println("Customer List in service Layer");

		return customerDAO.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> customer = customerDAO.findById(customerId);
		return customer.get();
	}

	@Override
	public Customer createCustomer(Customer cust) {
		System.out.println("Serice save customer" + cust.getCustomerId());
		if (cust.getCustomerId() != 0) {
			Optional<Customer> newCust = customerDAO.findById(cust.getCustomerId());

			System.out.println("find out customer is present or not :: " + newCust);
			if (newCust.isPresent()) {
				System.out.println("Customer present save it ");
				Customer newcust = customerDAO.save(cust);
				//documentDAO.saveAll(cust.getDocument());
				List<Document> docList = cust.getDocument();
				System.out.println("retrive doc"+docList.size());
				for (Document doc : docList) {
					System.out.println("Saving dcument-"+doc.getDocumentName());
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				}
				return cust;

			} else {
				Customer newcust= customerDAO.save(cust);
				System.out.println("Saving new customer");
				List<Document> docList = cust.getDocument();
				System.out.println("retrive doc"+docList.size());
				for (Document doc : docList) {
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				}
				return cust;
			}
		} else {
			throw new CustomerNotFoundException("Customer Not Found : " + cust.getCustomerId());
		}

	}
	
	@Override
	public Customer updateCustomer(Customer cust) {
		
		System.out.println("Serice save customer" + cust.getCustomerId());
		if (cust.getCustomerId() != 0) {
			Optional<Customer> newCust = customerDAO.findById(cust.getCustomerId());

			System.out.println("find out customer is present or not :: " + newCust);
			if (newCust.isPresent()) {
				System.out.println("Customer present save it ");
				Customer newcust = customerDAO.save(cust);
				//documentDAO.saveAll(cust.getDocument());
				List<Document> docList = cust.getDocument();
				System.out.println("retrive doc"+docList.size());
				for (Document doc : docList) {
					System.out.println("Saving document-"+doc.getDocumentName());
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				}
				return cust;

			} else {
				Customer newcust= customerDAO.save(cust);
				System.out.println("Saving new customer");
				List<Document> docList = cust.getDocument();
				System.out.println("retrive doc"+docList.size());
				for (Document doc : docList) {
					doc.setCustomer(newcust);
					documentDAO.save(doc);
				}
				return cust;
			}
		}else {
			throw new CustomerNotFoundException("Customer Not Found : " + cust.getCustomerId());
		}
		
	}

	public void deleteCustomer(int customerId) {
		System.out.println("customerId :: "+customerId);
		documentDAO.DeleteDocumentByCustomerID(customerId);
		customerDAO.DeleteCustomerByCustomerID(customerId);
		System.out.println("Deleted the customer ");
		
	}
	
	
	
	//getCustomerbyGender
		public List<Customer> getCustomerbyGender(String gender) {
			System.out.println("Gender Match list " +gender);
			
		   //customerDAO.CustomerByGender(gender);
			System.out.println("get the customer list");
			List<Customer> customerList = customerDAO.CustomerByGender(gender);
			List<Customer> customerListWith = customerList.stream().distinct().collect(Collectors.toList());
			System.out.println("Removed duplicate");
			return customerListWith;
		}
		 
}
