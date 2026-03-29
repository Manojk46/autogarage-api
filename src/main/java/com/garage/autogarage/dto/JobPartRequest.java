package com.garage.autogarage.dto;

import lombok.Data;

@Data
public class JobPartRequest {
    private Long serviceJobId;
    private String partName;
    private Integer quantity;
    private Double unitPrice;
}