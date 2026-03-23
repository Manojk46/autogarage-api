package com.garage.autogarage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garage.autogarage.entity.Customer;
import com.garage.autogarage.entity.Vehicle;
import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
 boolean existsBynumberplate(String numberplate);
 List<Vehicle> findByCustomer(Customer customer);
}
