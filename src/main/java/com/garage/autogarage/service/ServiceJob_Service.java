package com.garage.autogarage.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.autogarage.Exception.BadRequest;
import com.garage.autogarage.Exception.ResourceNotFound;
import com.garage.autogarage.dto.ServiceJobRequest;
import com.garage.autogarage.dto.ServiceJobResponse;
import com.garage.autogarage.entity.Appointment;
import com.garage.autogarage.entity.AppointmentStatus;
import com.garage.autogarage.entity.JobStatus;
import com.garage.autogarage.entity.Mechanic;
import com.garage.autogarage.entity.ServiceJob;
import com.garage.autogarage.repository.AppointmentRepository;
import com.garage.autogarage.repository.MechanicRepository;
import com.garage.autogarage.repository.ServiceJobRepository;

@Service
public class ServiceJob_Service {
	@Autowired
	ServiceJobRepository repository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	MechanicRepository mechanicRepository;
	@Autowired
	InvoiceService invoiceService;

	public ServiceJobResponse addjob(ServiceJobRequest request) {
		Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFound("Appointment not found"));

        if (repository.existsByAppointmentId(request.getAppointmentId())) {
            throw new BadRequest("Service job already exists for this appointment");
        }

        Mechanic mechanic = null;
        if (request.getMechanicId() != null) {
            mechanic = mechanicRepository.findById(request.getMechanicId())
                    .orElseThrow(() -> new ResourceNotFound("Mechanic not found"));
        } else if (appointment.getMechanic() != null) {
            mechanic = appointment.getMechanic();
        }

        appointment.setStatus(AppointmentStatus.IN_PROGRESS);
        appointmentRepository.save(appointment);

        ServiceJob job = ServiceJob.builder()
                .appointment(appointment)
                .mechanic(mechanic)
                .status(JobStatus.PENDING)
                .description(request.getDescription())
                .serviceCharge(request.getServiceCharge())
                .startedAt(LocalDateTime.now())
                .build();

        ServiceJob saved = repository.save(job);
        return mapToResponse(saved);
	}
	
	 public List<ServiceJobResponse> getAllJobs() {
	        return repository.findAll()
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	    }

	    public ServiceJobResponse getJobById(Long id) {
	        ServiceJob job = repository.findById(id)
	                .orElseThrow(() -> new ResourceNotFound("Service job not found with id: " + id));
	        return mapToResponse(job);
	    }

	    public List<ServiceJobResponse> getJobsByStatus(String status) {
	        JobStatus jobStatus = JobStatus.valueOf(status.toUpperCase());
	        return repository.findByStatus(jobStatus)
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	    }

	    public List<ServiceJobResponse> getJobsByMechanic(Long mechanicId) {
	        return repository.findByMechanicId(mechanicId)
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	    }

	    public ServiceJobResponse updateJobStatus(Long id, String status) {
	        ServiceJob job = repository.findById(id)
	                .orElseThrow(() -> new ResourceNotFound("Service job not found with id: " + id));

	        JobStatus newStatus = JobStatus.valueOf(status.toUpperCase());

	        if (job.getStatus() == JobStatus.COMPLETED) {
	            throw new BadRequest("Job is already completed");
	        }
	        if (job.getStatus() == JobStatus.IN_PROGRESS && newStatus == JobStatus.PENDING) {
	            throw new BadRequest("Cannot move job back to PENDING");
	        }

	        job.setStatus(newStatus);

	        if (newStatus == JobStatus.COMPLETED) {
	            job.setCompletedAt(LocalDateTime.now());
	            job.getAppointment().setStatus(AppointmentStatus.COMPLETED);
	            appointmentRepository.save(job.getAppointment());
	            ServiceJob saved = repository.save(job);
	            invoiceService.generateInvoice(saved.getId());
	            return mapToResponse(saved);
	        }

	        ServiceJob updated = repository.save(job);
	        return mapToResponse(updated);
	    }
	
	private ServiceJobResponse mapToResponse(ServiceJob job) {
        Appointment a = job.getAppointment();
        return new ServiceJobResponse(
        		job.getId(), 
        		a.getId(), 
        		a.getServicetype(), 
        		a.getVehicle().getModel(), 
        		a.getVehicle().getNumberplate(), 
        		a.getVehicle().getCustomer().getName(), 
        		job.getMechanic() != null ? job.getMechanic().getId() : null,
                job.getMechanic() != null ? job.getMechanic().getName() : null,
                job.getStatus().name(),
                job.getDescription(),
                job.getServiceCharge(),
                job.getStartedAt() != null ? job.getStartedAt().toString() : null,
                job.getCompletedAt() != null ? job.getCompletedAt().toString() : null);
    }
}
