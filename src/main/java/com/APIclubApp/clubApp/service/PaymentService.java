package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> listAllPayments();

    Payment savePayment(Payment payment);

    Payment getPaymentById(Long id);

    void deletePayment(Long id);
}
