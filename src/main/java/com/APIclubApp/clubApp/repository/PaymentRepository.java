package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
