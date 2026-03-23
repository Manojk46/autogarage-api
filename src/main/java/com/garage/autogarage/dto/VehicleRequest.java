package com.garage.autogarage.dto;

import lombok.Data;

@Data
public class VehicleRequest {

	private String brand;
	private String model;
	private String numberplate;
	private int year;
	private String Color;
	private Long customerid;
}
