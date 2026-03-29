package com.garage.autogarage.controller;

import com.garage.autogarage.dto.JobPartRequest;
import com.garage.autogarage.dto.JobPartResponse;
import com.garage.autogarage.service.JobPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parts")
@RequiredArgsConstructor
public class JobPartController {

    private final JobPartService jobPartService;

    @PostMapping
    public ResponseEntity<JobPartResponse> addPart(@RequestBody JobPartRequest request) {
        return ResponseEntity.ok(jobPartService.addPart(request));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobPartResponse>> getPartsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobPartService.getPartsByJob(jobId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePart(@PathVariable Long id) {
        return ResponseEntity.ok(jobPartService.deletePart(id));
    }
}