package com.garage.autogarage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.garage.autogarage.dto.CustomerRequest;
import com.garage.autogarage.dto.CustomerResponse;
import com.garage.autogarage.service.CustomerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customer) {
	   	return ResponseEntity.ok(service.addcustomer(customer));
		
	}
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> getallcustomer(){
		return ResponseEntity.ok(service.getallCustomer());
	}
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getcustomer(@PathVariable Long id){
		return ResponseEntity.ok(service.getcustomer(id));
	}
	@PutMapping("{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id,@RequestBody CustomerRequest request){
		return ResponseEntity.ok(service.updateCustomer(id,request));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<CustomerResponse> deleteCustomerbyid(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteCustomerbyid(id));
	}
	
}
