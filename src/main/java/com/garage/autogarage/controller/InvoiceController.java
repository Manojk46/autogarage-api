package com.garage.autogarage.controller;

import com.garage.autogarage.dto.InvoiceResponse;
import com.garage.autogarage.service.InvoiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Invoice", description = "Invoice generation and payment APIs")
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

    @GetMapping("/part/{jobId}")
    public ResponseEntity<InvoiceResponse> getInvoiceByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(invoiceService.getInvoiceByJob(jobId));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<InvoiceResponse> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.markAsPaid(id));
    }

    @Operation(summary = "Get today's revenue",
    		description = "Returns total revenue from all paid invoices for today.")
    @GetMapping("/revenue/today")
    public ResponseEntity<Double> getDailyRevenue() {
        return ResponseEntity.ok(invoiceService.getDailyRevenue());
    }
}