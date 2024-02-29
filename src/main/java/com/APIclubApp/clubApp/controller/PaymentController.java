package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.PaymentDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Payment;
import com.APIclubApp.clubApp.service.PaymentService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/list")
    public ResponseEntity<List<Payment>> listAllPayments() {
        return ResponseEntity.ok(paymentService.listAllPayments());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        ResponseEntity<Payment> response;

        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            response = ResponseEntity.ok(payment);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<Payment> savePayment(@RequestBody PaymentDTO payment) {
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Payment> updateCategory(@RequestBody PaymentDTO payment){
        ResponseEntity<Payment> response;
        if (payment.getPaymentId() != null && paymentService.getPaymentById(payment.getPaymentId()) != null){
            response = ResponseEntity.ok(paymentService.savePayment(payment));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
