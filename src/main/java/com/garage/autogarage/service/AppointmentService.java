package com.garage.autogarage.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.autogarage.Exception.BadRequest;
import com.garage.autogarage.Exception.ResourceNotFound;
import com.garage.autogarage.dto.AppointmentRequest;
import com.garage.autogarage.dto.AppointmentResponse;
import com.garage.autogarage.entity.Appointment;
import com.garage.autogarage.entity.AppointmentStatus;
import com.garage.autogarage.entity.Mechanic;
import com.garage.autogarage.entity.Vehicle;
import com.garage.autogarage.repository.AppointmentRepository;
import com.garage.autogarage.repository.MechanicRepository;
import com.garage.autogarage.repository.VehicleRepository;

@Service
public class AppointmentService {
	@Autowired
    AppointmentRepository repository;
	@Autowired
	VehicleRepository vehicleRepository;
	@Autowired
	MechanicRepository mechanicRepository;

	public AppointmentResponse bookAppointment(AppointmentRequest request) {
		Vehicle vehicle=vehicleRepository.findById(request.getVehicleid())
				.orElseThrow(()->new ResourceNotFound("Vehicle Not FOund"));
		
		LocalDate date=LocalDate.parse(request.getDate());
		LocalTime time=LocalTime.parse(request.getTimeslot());
		Mechanic mechanic=null;
		if(request.getMechanicid()!=null) {
			mechanic=mechanicRepository.findById(request.getMechanicid())
					.orElseThrow(()->new ResourceNotFound("Mechanic Not FOund"));
			boolean slottaken=repository.existsByMechanicIdAndDateAndTimeslot(
					mechanic.getId(), date, time);
			if(slottaken) {
				throw new BadRequest("Mechanic is Booked at date and time");
			}
		}
		
		
		Appointment appointment=Appointment.builder()
				.vehicle(vehicle)
				.mechanic(mechanic)
				.date(date)
				.timeslot(time)
				.servicetype(request.getServicetype())
				.notesString(request.getNote())
				.status(AppointmentStatus.SCHEDULED)
				.build();
		Appointment savedAppointment=repository.save(appointment);
		return maptResponse(savedAppointment);
	}
	
	public List<AppointmentResponse> getAllAppointment() {
		return repository.findAll()
				.stream()
				.map(this::maptResponse)
				.collect(Collectors.toList());
	}
	
	private AppointmentResponse maptResponse(Appointment appointment) {
		return new AppointmentResponse(
				appointment.getId(),
				appointment.getVehicle().getId(),
				appointment.getVehicle().getModel(), 
				appointment.getVehicle().getCustomer().getName(),  
				appointment.getMechanic().getId(),
				appointment.getMechanic().getName(), 
				appointment.getDate().toString(), 
				appointment.getTimeslot().toString(), 
				appointment.getServicetype(), 
				appointment.getStatus().toString());
	}

	public AppointmentResponse getAppointmentByVehicleId(Long id) {
		Appointment appointment=repository.findById(id)
				.orElseThrow(()->new RuntimeException("Not Found"));
		return maptResponse(appointment);
	}

	public List<AppointmentResponse> getAppointmentByDate(String date) {
		LocalDate date2=LocalDate.parse(date);
		return repository.findByDate(date2)
				.stream().map(this::maptResponse).collect(Collectors.toList());
	}

	public List<AppointmentResponse> getAppointmentByCustomerId(Long id) {
		return repository.findByVehicleCustomerId(id)
				.stream().map(this::maptResponse).collect(Collectors.toList());
	}

	public AppointmentResponse cancelAppointment(Long id) {
		Appointment appointment=repository.findById(id)
				.orElseThrow(()->new RuntimeException("Appointment Not Found"));
		if(appointment.getStatus() == AppointmentStatus.COMPLETED) {
			throw new RuntimeException("Cannot cancel a completed appointment");
		}
		appointment.setStatus(AppointmentStatus.CANCELLED);
		Appointment updateAppointment=repository.save(appointment);
		return maptResponse(updateAppointment);
	}
}
