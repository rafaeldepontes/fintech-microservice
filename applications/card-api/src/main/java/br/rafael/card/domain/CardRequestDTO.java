package br.rafael.card.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardRequestDTO {

    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;

}
