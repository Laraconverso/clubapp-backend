package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.PaymentDTO;
import com.APIclubApp.clubApp.model.Payment;
import com.APIclubApp.clubApp.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Lista todos los pagos")
    @GetMapping("/list")
    @PreAuthorize("hasRole('Admin', 'Player')")
    public ResponseEntity<List<Payment>> listAllPayments() {
        return ResponseEntity.ok(paymentService.listAllPayments());
    }
    @Operation(summary = "Obtiene un pago por su ID")
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('Admin', 'Player')")
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

    @Operation(summary = "Crea un pago")
    @PostMapping("/save")
    @PreAuthorize("hasRole( 'Admin', 'Player')")
    public ResponseEntity<Payment> savePayment(@RequestBody PaymentDTO payment) {
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }

    @Operation(summary = "Actualiza un pago")
    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin', 'Player')")
    public ResponseEntity<Payment> updateCategory(@RequestBody PaymentDTO payment){
        ResponseEntity<Payment> response;
        if (payment.getPaymentId() != null && paymentService.getPaymentById(payment.getPaymentId()) != null){
            response = ResponseEntity.ok(paymentService.savePayment(payment));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Elimina un pago por su ID")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
