package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobPartResponse {
    private Long id;
    private Long serviceJobId;
    private String partName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
