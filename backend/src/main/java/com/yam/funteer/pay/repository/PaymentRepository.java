package com.yam.funteer.pay.repository;

import com.yam.funteer.pay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
