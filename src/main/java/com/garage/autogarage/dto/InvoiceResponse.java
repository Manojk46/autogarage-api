package com.garage.autogarage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceResponse {
    private Long id;
    private Long serviceJobId;
    private String customerName;
    private String vehicleBrand;
    private String vehicleModel;
    private String licensePlate;
    private String mechanicName;
    private String serviceType;
    private Double serviceCharge;
    private Double partsTotal;
    private Double subTotal;
    private Double gstAmount;
    private Double totalAmount;
    private String paymentStatus;
    private String generatedAt;
    private String paidAt;
}