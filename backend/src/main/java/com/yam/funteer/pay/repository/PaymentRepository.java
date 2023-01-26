package com.yam.funteer.pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yam.funteer.pay.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
