package com.garage.autogarage.dto;

import lombok.Data;

@Data
public class ServiceJobRequest {
	private Long appointmentId;
    private Long mechanicId;
    private String description;
    private Double serviceCharge;
}
