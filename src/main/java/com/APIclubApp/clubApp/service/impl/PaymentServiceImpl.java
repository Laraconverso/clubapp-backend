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
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;


    @Override
    public Payment savePayment(PaymentDTO paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        Player player = playerRepository.findByUserDni(paymentDto.getPlayer().getUserDni());
        if(player==null) throw new RuntimeException("Player not found"); //error 500
        payment.setPlayer(player);
        return paymentRepository.save(payment);
    }



    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
