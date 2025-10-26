package br.rafael.creditvalidator.api.application.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientCardRequest {
    
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;

}
