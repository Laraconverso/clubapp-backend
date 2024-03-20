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
//    le paso el DTO reducido para q cuando pague el jugador
//    no tenga que cargar TODOS sus datos (de usuario) de la pagina
//    igualmente queda abierto a modificaciones por nececidad del form de pago
}
