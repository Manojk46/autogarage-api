package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {
	private String brand;
	private String model;
	private int year;
	private String numberplate;
	private String customername;
}
