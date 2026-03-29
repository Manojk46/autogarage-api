package com.garage.autogarage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.garage.autogarage.entity.Invoice;
import com.garage.autogarage.entity.PaymentStatus;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	Optional<Invoice> findByServiceJobId(Long serviceJobId);
    List<Invoice> findByPaymentStatus(PaymentStatus status);

    
    @Query("SELECT COALESCE(SUM(i.totalAmount), 0) FROM Invoice i WHERE DATE(i.paidAt) = CURRENT_DATE AND i.paymentStatus = 'PAID'")
    Double getDailyRevenue();
}
