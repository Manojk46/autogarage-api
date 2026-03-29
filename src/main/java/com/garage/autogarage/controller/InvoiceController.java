package com.garage.autogarage.controller;

import com.garage.autogarage.dto.InvoiceResponse;
import com.garage.autogarage.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<InvoiceResponse> getInvoiceByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(invoiceService.getInvoiceByJob(jobId));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<InvoiceResponse> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.markAsPaid(id));
    }

    @GetMapping("/revenue/today")
    public ResponseEntity<Double> getDailyRevenue() {
        return ResponseEntity.ok(invoiceService.getDailyRevenue());
    }
}