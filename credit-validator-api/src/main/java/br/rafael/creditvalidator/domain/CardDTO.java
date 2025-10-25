package br.rafael.creditvalidator.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardDTO {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal income;
    private BigDecimal limit;


}