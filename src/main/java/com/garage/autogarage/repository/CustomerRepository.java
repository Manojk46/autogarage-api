package com.garage.autogarage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garage.autogarage.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  boolean existsByemail(String email);
}
