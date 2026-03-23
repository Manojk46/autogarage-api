package com.garage.autogarage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garage.autogarage.entity.Mechanic;
import com.garage.autogarage.entity.MechanicStatus;

import java.util.List;


public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
 List<Mechanic> findByStatus(MechanicStatus status);
 boolean existsByEmail(String email);

}
