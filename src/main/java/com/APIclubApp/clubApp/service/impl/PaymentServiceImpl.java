package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.PaymentDTO;
import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Payment;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.PaymentRepository;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PlayerRepository playerRepository;


    @Override
    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    @Autowired
    private ObjectMapper objectMapper;

//    @Override
//    public Payment savePayment(PaymentDTO payment) {
//
//        Payment p = new Payment();
//        p.setPaymentId(payment.getPaymentId());
//        p.setAmount(payment.getAmount());
//        PlayerFormDTO player = payment.getPlayer();
//        Player player1 =objectMapper.convertValue(player,Player.class);
//        p.setPlayer(player1);
//        return paymentRepository.save(p);
//    }

    @Override
    public Payment savePayment(PaymentDTO paymentDto) {
        // Convert PaymentDTO to Payment using ObjectMapper
        Payment payment = objectMapper.convertValue(paymentDto, Payment.class);

        // Fetch the Player object from the database by its ID
        Player player = playerRepository.findByUserDni(paymentDto.getPlayer().getUserDni());
                //.orElseThrow(() -> new RuntimeException("Player not found"));

        // Set the fetched Player object in the Payment object
        payment.setPlayer(player);

        // Save the Payment object
        return paymentRepository.save(payment);
    }

//    @Override
//    public Payment savePayment(PaymentDTO paymentDto) {
//        Payment payment = objectMapper.convertValue(paymentDto, Payment.class);
//        return paymentRepository.save(payment);
//    }
    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
