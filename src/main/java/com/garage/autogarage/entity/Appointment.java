package com.garage.autogarage.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "vehicle_id",nullable = false)
	private Vehicle vehicle;
	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	private Mechanic mechanic;
	@Column(nullable = false)
	private LocalDate date;
	@Column(nullable = false)
	private LocalTime timeslot;
	private String servicetype;
	private String notesString;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
}
