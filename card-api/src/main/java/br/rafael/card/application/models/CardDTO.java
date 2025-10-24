package br.rafael.card.application.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDTO {
    
    private Long id;
    private String name;
    private String brand;
    private BigDecimal income;
    private BigDecimal limit;

}
