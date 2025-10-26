package br.rafael.creditvalidator.api.application.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientStatusInfo {

    private String cpf;
    private BigDecimal income;

}
