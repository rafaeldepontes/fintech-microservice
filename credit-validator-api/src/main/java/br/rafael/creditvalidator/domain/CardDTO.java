package br.rafael.creditvalidator.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardDTO {

    private String name;
    private String brand;
    private BigDecimal limit;


}