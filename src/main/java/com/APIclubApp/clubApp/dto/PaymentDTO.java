package com.APIclubApp.clubApp.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private Integer amount;
    private PlayerFormDTO player;
    //abierto a modificaciones de form de pago
}
