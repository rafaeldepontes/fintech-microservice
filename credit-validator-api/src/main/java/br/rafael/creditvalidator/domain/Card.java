package br.rafael.creditvalidator.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
    
    private String name;
    private String brand;
    private BigDecimal approvedLimit;

}
