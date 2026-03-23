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

import com.garage.autogarage.dto.MechanicRequest;
import com.garage.autogarage.dto.MechanicResponse;
import com.garage.autogarage.service.MechanicService;

@RestController
@RequestMapping("mechanic")
public class MechanicController {
	
	@Autowired
	MechanicService service;
	
	@PostMapping
	public ResponseEntity<MechanicResponse> addMechanic(@RequestBody MechanicRequest request){
		return ResponseEntity.ok(service.addMechanic(request));
	}
	@GetMapping
	public ResponseEntity<List<MechanicResponse>> getallMechanic(){
		return ResponseEntity.ok(service.getallMEchanic());
	}
	@GetMapping("{id}")
	public ResponseEntity<MechanicResponse> getMechanicByid(@PathVariable Long id){
		return ResponseEntity.ok(service.getMechanicByid(id));
	}
	 @PutMapping("{id}")
	 public ResponseEntity<MechanicResponse> updateMechanic( @PathVariable Long id,@RequestBody MechanicRequest request) {
	      return ResponseEntity.ok(service.updateMechanic(id, request));
	 }

	 @DeleteMapping("{id}")
	 public ResponseEntity<MechanicResponse> deleteMechanic(@PathVariable Long id) {
	     return ResponseEntity.ok(service.deleteMechanic(id));
	}
	 @GetMapping("/avaiable")
	 public ResponseEntity<List<MechanicResponse>> getMechanicByAvaiable(){
		 return ResponseEntity.ok(service.getMechanicByAvaiable());
	 }
}
