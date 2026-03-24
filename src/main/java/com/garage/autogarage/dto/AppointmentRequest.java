package com.garage.autogarage.dto;

import lombok.Data;

@Data
public class AppointmentRequest {
 private Long vehicleid;
 private Long mechanicid;
 private String date;
 private String timeslot;
 private String servicetype;
 private String note;
}
