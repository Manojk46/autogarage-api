package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MechanicResponse {
	    private Long id;
	    private String name;
	    private String email;
	    private String phone;
	    private String specialization;
	    private String status;
}
