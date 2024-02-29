package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.PaymentDTO;
import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.model.Payment;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.PaymentRepository;
import com.APIclubApp.clubApp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> listAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment savePayment(PaymentDTO payment) {
        Payment p = new Payment();
        p.setPaymentId(payment.getPaymentId());
        p.setAmount(payment.getAmount());
        Player player = new Player();
        player.setUserName(payment.getPlayer().getUserName());
        player.setUserLastname(payment.getPlayer().getUserLastname());
        player.setUserDni(payment.getPlayer().getUserDni());
        player.setUserEmail(payment.getPlayer().getUserEmail());
        player.setUserAddress(payment.getPlayer().getUserAddress());
        player.setPlayerBirthdate(payment.getPlayer().getPlayerBirthdate());
        p.setPlayer(player);
        return paymentRepository.save(p);
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
