package com.garage.autogarage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_parts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_job_id", nullable = false)
    private ServiceJob serviceJob;

    @Column(nullable = false)
    private String partName;      

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double unitPrice;    
    private Double totalPrice;    
}