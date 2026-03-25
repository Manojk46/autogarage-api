package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceJobResponse {

	private Long id;
    private Long appointmentId;
    private String serviceType;
    private String vehicleModel;
    private String numberplate;
    private String customerName;
    private Long mechanicId;
    private String mechanicName;
    private String status;
    private String description;
    private Double serviceCharge;
    private String startedAt;
    private String completedAt;
}
