package com.garage.autogarage.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.garage.autogarage.entity.JobStatus;
import com.garage.autogarage.entity.ServiceJob;

public interface ServiceJobRepository extends JpaRepository<ServiceJob, Long> {

	boolean existsByAppointmentId(Long appointmentId);

	List<ServiceJob> findByStatus(JobStatus jobStatus);

	List<ServiceJob> findByMechanicId(Long mechanicId);


}
