package com.garage.autogarage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.autogarage.dto.VehicleRequest;
import com.garage.autogarage.dto.VehicleResponse;
import com.garage.autogarage.service.VehicleService;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
	
  @Autowired
  VehicleService service;
  
  @PostMapping
  public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest request){
	  return ResponseEntity.ok(service.addVehicle(request));
  }
  @GetMapping
  public ResponseEntity<List<VehicleResponse>> getAllVehicle(){
	  return ResponseEntity.ok(service.getAllVehicle());
  }
  @GetMapping("{id}")
  public ResponseEntity<VehicleResponse> getVehicleByid(@PathVariable Long id){
	  return ResponseEntity.ok(service.getVehicleByid(id));
  }
  @PutMapping("{id}")
  public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Long id,@RequestBody VehicleRequest request){
	  return ResponseEntity.ok(service.updateVehicle(id,request));
  }
  @DeleteMapping("{id}")
  public ResponseEntity<VehicleResponse> deleteVehicleByid(@PathVariable Long id){
	  return ResponseEntity.ok(service.deleteVehicleByid(id));
  }
  @GetMapping("/customer/{id}")
  public ResponseEntity<List<VehicleResponse>> getVehicleByCustomerId(@PathVariable Long id){
	  return ResponseEntity.ok(service.getVehicleByCustomerId(id));
  }
}
