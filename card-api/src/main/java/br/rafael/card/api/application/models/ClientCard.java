package br.rafael.card.api.application.models;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientCard {
    
    private String name;
    private String brand;
    private BigDecimal limit; 

}
