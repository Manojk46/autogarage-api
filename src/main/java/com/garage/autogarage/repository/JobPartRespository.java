package com.garage.autogarage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.garage.autogarage.entity.JobPart;

public interface JobPartRespository extends JpaRepository<JobPart,Long> {
	List<JobPart> findByServiceJobId(Long serviceJobId);

    
    @Query("SELECT COALESCE(SUM(p.totalPrice), 0) FROM JobPart p WHERE p.serviceJob.id = :jobId")
    Double sumTotalPriceByServiceJobId(@Param("jobId") Long jobId);
}
