package com.garage.autogarage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.autogarage.dto.AppointmentRequest;
import com.garage.autogarage.dto.AppointmentResponse;
import com.garage.autogarage.service.AppointmentService;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
	@Autowired
	AppointmentService service;
	
	@PostMapping
	public ResponseEntity<AppointmentResponse> bookAppointment(@RequestBody AppointmentRequest request){
		return ResponseEntity.ok(service.bookAppointment(request));
	}
	@GetMapping
	public ResponseEntity<List<AppointmentResponse>> getAllAppointment(){
		return ResponseEntity.ok(service.getAllAppointment());
	}
	@GetMapping("{id}")
	public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id){
		return ResponseEntity.ok(service.getAppointmentByVehicleId(id));
	}
	@GetMapping("date/{date}")
	public ResponseEntity<List<AppointmentResponse>> getAppointmentByDate(@PathVariable String date){
		return ResponseEntity.ok(service.getAppointmentByDate(date));
	}
	@GetMapping("customer/{id}")
	public ResponseEntity<List<AppointmentResponse>> getAppointmentByCustomerId(@PathVariable Long id){
		return ResponseEntity.ok(service.getAppointmentByCustomerId(id));
	}
	@PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelAppointment(id));
    }
}
