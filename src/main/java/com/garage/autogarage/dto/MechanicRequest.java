package com.garage.autogarage.dto;

import com.garage.autogarage.entity.MechanicStatus;

import lombok.Data;

@Data
public class MechanicRequest {
	    private String name;
	    private String email;
	    private String phone;
	    private MechanicStatus statu;
	    private String specialization;
	    private Long userId;
}
