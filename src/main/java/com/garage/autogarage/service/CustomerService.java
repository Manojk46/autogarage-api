package com.garage.autogarage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.autogarage.dto.CustomerRequest;
import com.garage.autogarage.dto.CustomerResponse;
import com.garage.autogarage.entity.Customer;
import com.garage.autogarage.repository.CustomerRepository;

@Service
public class CustomerService {
 @Autowired
 private CustomerRepository repository;

 public CustomerResponse addcustomer(CustomerRequest request) {
	if(repository.existsByemail(request.getEmail())) {
		throw new RuntimeException("Customer alreday Exists");
	}
	Customer customer=new Customer();
	customer.setName(request.getName());
	customer.setAddress(request.getAddress());
	customer.setEmail(request.getEmail());
	customer.setPhone(request.getPhone());
	 Customer savedCustomer= repository.save(customer);
	 return maptoResponse(savedCustomer);
 }

 public List<CustomerResponse> getallCustomer() {
	return repository.findAll()
			.stream()
			.map(this::maptoResponse)
			.collect(Collectors.toList());
 }
 
 public CustomerResponse getcustomer(Long id) {
		Customer customer=repository.findById(id)
				.orElseThrow(()->new RuntimeException("Customer Not Found"));
				return maptoResponse(customer);
 }
 public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
		Customer customer=repository.findById(id)
				.orElseThrow(()->new RuntimeException("Customer Not FOund"));
		customer.setName(request.getName());
		customer.setAddress(request.getAddress());
		customer.setEmail(request.getEmail());
		customer.setPhone(request.getPhone());
		Customer updatedCustomer=repository.save(customer);
		return maptoResponse(updatedCustomer);
	 }

	 public CustomerResponse deleteCustomerbyid(Long id) {
		 Customer customer=repository.findById(id)
				 .orElseThrow(()->new RuntimeException("Customer Not FOund"));
		 repository.delete(customer);
		return maptoResponse(customer);
	 }
 
 private CustomerResponse maptoResponse(Customer customer) {
	 return new CustomerResponse(customer.getName(),
			 customer.getPhone(),
			 customer.getAddress());
 }

 
}

