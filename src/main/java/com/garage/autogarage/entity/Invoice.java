package com.garage.autogarage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "service_job_id", nullable = false)
    private ServiceJob serviceJob;

    private Double serviceCharge;   

    private Double partsTotal;      

    private Double subTotal;       

    private Double gstAmount;       

    private Double totalAmount;     

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime generatedAt;

    private LocalDateTime paidAt;
}