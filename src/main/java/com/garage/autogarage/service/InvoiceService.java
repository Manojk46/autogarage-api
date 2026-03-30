package com.garage.autogarage.service;

import com.garage.autogarage.Exception.BadRequest;
import com.garage.autogarage.Exception.ResourceNotFound;
import com.garage.autogarage.dto.InvoiceResponse;
import com.garage.autogarage.entity.*;
import com.garage.autogarage.repository.InvoiceRepository;
import com.garage.autogarage.repository.JobPartRespository;
import com.garage.autogarage.repository.ServiceJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ServiceJobRepository serviceJobRepository;
    private final JobPartRespository jobPartRepository;

    private static final double GST_RATE = 0.18; 

    public InvoiceResponse generateInvoice(Long serviceJobId) {

        ServiceJob job = serviceJobRepository.findById(serviceJobId)
                .orElseThrow(() -> new ResourceNotFound("Service job not found"));

        
        if (invoiceRepository.findByServiceJobId(serviceJobId).isPresent()) {
            throw new BadRequest("Invoice already generated for this job");
        }

        
        Double serviceCharge = job.getServiceCharge() != null ? job.getServiceCharge() : 0.0;
        Double partsTotal = jobPartRepository.sumTotalPriceByServiceJobId(serviceJobId);
        Double subTotal = serviceCharge + partsTotal;
        Double gstAmount = Math.round(subTotal * GST_RATE * 100.0) / 100.0;
        Double totalAmount = Math.round((subTotal + gstAmount) * 100.0) / 100.0;

        
        Invoice invoice = Invoice.builder()
                .serviceJob(job)
                .serviceCharge(serviceCharge)
                .partsTotal(partsTotal)
                .subTotal(subTotal)
                .gstAmount(gstAmount)
                .totalAmount(totalAmount)
                .paymentStatus(PaymentStatus.UNPAID)
                .generatedAt(LocalDateTime.now())
                .build();

        Invoice saved = invoiceRepository.save(invoice);
        return mapToResponse(saved);
    }

    
    public InvoiceResponse getInvoiceByJob(Long jobId) {
        Invoice invoice = invoiceRepository.findByServiceJobId(jobId)
                .orElseThrow(() -> new ResourceNotFound("Invoice not found for job: " + jobId));
        return mapToResponse(invoice);
    }

    
    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Invoice not found with id: " + id));
        return mapToResponse(invoice);
    }

    
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

   
    public InvoiceResponse markAsPaid(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Invoice not found with id: " + id));

        if (invoice.getPaymentStatus() == PaymentStatus.PAID) {
            throw new RuntimeException("Invoice is already paid");
        }

        invoice.setPaymentStatus(PaymentStatus.PAID);
        invoice.setPaidAt(LocalDateTime.now());

        Invoice updated = invoiceRepository.save(invoice);
        return mapToResponse(updated);
    }

    
    public Double getDailyRevenue() {
        return invoiceRepository.getDailyRevenue();
    }

   
    private InvoiceResponse mapToResponse(Invoice invoice) {
        ServiceJob job = invoice.getServiceJob();
        Appointment a = job.getAppointment();
        return new InvoiceResponse(
                invoice.getId(),
                job.getId(),
                a.getVehicle().getCustomer().getName(),
                a.getVehicle().getBrand(),
                a.getVehicle().getModel(),
                a.getVehicle().getNumberplate(),
                job.getMechanic() != null ? job.getMechanic().getName() : "Not Assigned",
                a.getServicetype(),
                invoice.getServiceCharge(),
                invoice.getPartsTotal(),
                invoice.getSubTotal(),
                invoice.getGstAmount(),
                invoice.getTotalAmount(),
                invoice.getPaymentStatus().name(),
                invoice.getGeneratedAt().toString(),
                invoice.getPaidAt() != null ? invoice.getPaidAt().toString() : null
        );
    }
}
