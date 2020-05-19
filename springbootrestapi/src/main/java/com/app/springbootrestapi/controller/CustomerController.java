package com.app.springbootrestapi.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.springbootrestapi.entity.Customer;
import com.app.springbootrestapi.exception.CustomerNotFoundException;
import com.app.springbootrestapi.service.CustomerService;
import com.app.springbootrestapi.entity.Gender;


@RequestMapping("/customers")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// 1 get All Customer List
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomer() {
		System.out.println("Controller customer Layer ");
		List<Customer> listcust = customerService.getAllCustomerList();
		System.out.println("list of customer" + listcust);
		for (Customer customer : listcust) {
			System.out.println(customer);
		}
		return listcust;
	}


	// 2 Get Customer by Id
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId)
			throws CustomerNotFoundException {
		Customer customer = customerService.getCustomerById(customerId);
		if (customerId <= 0) {
			System.out.println("400 (BAD REQUEST) : " + customerId);
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
		if (customer == null) {
			System.out.println("customer is null in case 404 no found /Customer id is not found : " + customerId);
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	
	// 3 create customer
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Customer> createOrUpdateCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("Controller post method");
		Customer created = customerService.createCustomer(customer);
		return new ResponseEntity<Customer>(created, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	// 4 update customer
	@PutMapping
//	@ResponseStatus(HttpStatus.OK)
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("update customer..");
		Customer updated = customerService.updateCustomer(customer);
		return updated;
	}
	

	  // 5 delete customer 
	  @DeleteMapping(value = "/{customerId}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public @ResponseBody void deleteCustomer(@PathVariable("customerId") int customerId) 
	  { 
		  System.out.println("Start deleteCustomer....");
		  customerService.deleteCustomer(customerId); 
	  }
	 
	  // 6
	  @GetMapping(value = "/gender/{gender}")
	  @ResponseStatus(HttpStatus.OK)
	  public List<Customer> getCustomerByGender(@PathVariable("gender") String gender) {
		  System.out.println(" Get customerId with Gender...." + gender);
		  return customerService.getCustomerbyGender(gender);
	}

		// 7
	/*
	 * @RequestMapping(value = "/dob/{dateOfBirth}", method = RequestMethod.GET)
	 * 
	 * @ResponseStatus(HttpStatus.OK) public List<Customer>
	 * getCustomerByDOB(@PathVariable("dateOfBirth") String dateOfBirth) throws
	 * ParseException { System.out.println(dateOfBirth.toString());
	 * System.out.println(" Get customerId with Gender...."); Date dob =
	 * DateFormate.typecastToDate(dateOfBirth); return
	 * customerService.getCustomerbyDob(dob); }
	 */
		
		
		// 8 GenderWithDob
	/*
	 * @RequestMapping(value = "/{gender}/{dateOfBirth}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseStatus(HttpStatus.OK) public List<Customer>
	 * getCustomerByGenderWithDOB(@PathVariable("gender") String
	 * gender, @PathVariable("dateOfBirth") String dateOfBirth) throws
	 * ParseException {
	 * System.out.println("Enter in Get Customer by gender and date of birth");
	 * Gender genderParam = Gender.valueOf(gender); Date dob =
	 * DateFormate.typecastToDate(dateOfBirth); return
	 * customerService.getCustomerByGenderWithDob(genderParam, dob);
	 * 
	 * }
	 */
}
