package br.rafael.creditvalidator.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientStatus {
    
    private Client client;
    private List<Card> cards;

}
    