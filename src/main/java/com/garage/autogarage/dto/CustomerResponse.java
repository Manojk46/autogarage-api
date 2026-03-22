package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
	private String name;
	private String phone;
	private String address;
}
