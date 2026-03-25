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

import com.garage.autogarage.dto.ServiceJobRequest;
import com.garage.autogarage.dto.ServiceJobResponse;
import com.garage.autogarage.service.ServiceJob_Service;

@RestController
@RequestMapping("job")
public class ServiceJobController {
	@Autowired
	ServiceJob_Service service;
	
	@PostMapping
	public ResponseEntity<ServiceJobResponse> addjob(@RequestBody ServiceJobRequest request){
		return ResponseEntity.ok(service.addjob(request));
	}
	
	@GetMapping
    public ResponseEntity<List<ServiceJobResponse>> getAllJobs() {
        return ResponseEntity.ok(service.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceJobResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getJobById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceJobResponse>> getJobsByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(service.getJobsByStatus(status));
    }

    @GetMapping("/mechanic/{mechanicId}")
    public ResponseEntity<List<ServiceJobResponse>> getJobsByMechanic(
            @PathVariable Long mechanicId) {
        return ResponseEntity.ok(service.getJobsByMechanic(mechanicId));
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<ServiceJobResponse> updateJobStatus(
            @PathVariable Long id,
            @PathVariable String status) {
        return ResponseEntity.ok(service.updateJobStatus(id, status));
    }
}
