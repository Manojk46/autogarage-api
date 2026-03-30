package com.garage.autogarage.service;

import com.garage.autogarage.Exception.BadRequest;
import com.garage.autogarage.Exception.ResourceNotFound;
import com.garage.autogarage.dto.JobPartRequest;
import com.garage.autogarage.dto.JobPartResponse;
import com.garage.autogarage.entity.JobPart;
import com.garage.autogarage.entity.JobStatus;
import com.garage.autogarage.entity.ServiceJob;
import com.garage.autogarage.repository.JobPartRespository;
import com.garage.autogarage.repository.ServiceJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPartService {

    private final JobPartRespository jobPartRepository;
    private final ServiceJobRepository serviceJobRepository;

    
    public JobPartResponse addPart(JobPartRequest request) {
        ServiceJob job = serviceJobRepository.findById(request.getServiceJobId())
                .orElseThrow(() -> new ResourceNotFound("Service job not found"));

        
        if (job.getStatus() == JobStatus.COMPLETED) {
            throw new BadRequest("Cannot add parts to a completed job");
        }

        Double totalPrice = request.getQuantity() * request.getUnitPrice();

        JobPart part = JobPart.builder()
                .serviceJob(job)
                .partName(request.getPartName())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(totalPrice)
                .build();

        JobPart saved = jobPartRepository.save(part);
        return mapToResponse(saved);
    }

    
    public List<JobPartResponse> getPartsByJob(Long jobId) {
        return jobPartRepository.findByServiceJobId(jobId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    
    public String deletePart(Long id) {
        JobPart part = jobPartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Part not found with id: " + id));
        jobPartRepository.delete(part);
        return "Part deleted successfully";
    }

   
    private JobPartResponse mapToResponse(JobPart part) {
        return new JobPartResponse(
                part.getId(),
                part.getServiceJob().getId(),
                part.getPartName(),
                part.getQuantity(),
                part.getUnitPrice(),
                part.getTotalPrice()
        );
    }
}