package com.garage.autogarage.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garage.autogarage.entity.Appointment;
import com.garage.autogarage.entity.Vehicle;

import java.util.List;
import java.util.Optional;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	boolean existsByMechanicIdAndDateAndTimeslot(
            Long mechanicId, LocalDate date, LocalTime time);
	List<Appointment> findByDate(LocalDate date);
	List<Appointment> findByVehicleCustomerId(Long id);
	
}
