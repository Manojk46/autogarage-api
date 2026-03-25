package com.garage.autogarage.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job")
public class ServiceJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "appointment_id",nullable = false)
	private Appointment appointment;
	@ManyToOne
    @JoinColumn(name = "mechanic_id")	
	private Mechanic mechanic;
	@Column(nullable = false)
	private JobStatus status;
	private String description;
	private Double serviceCharge;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
}
