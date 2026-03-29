package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentResponse {
	 private Long id;
	 private Long vehicleid;
	 private String vechilename;
	 private String customername;
	 private Long mechanicid;
	 private String mechanicname;
	 private String date;
	 private String timeslot;
	 private String servicetype;
	 private String status;
}
