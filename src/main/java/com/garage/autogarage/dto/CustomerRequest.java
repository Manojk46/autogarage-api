package com.garage.autogarage.dto;

import lombok.Data;

@Data
public class CustomerRequest {
	private String name;
	private String email;
	private String phone;
	private String address;
}
