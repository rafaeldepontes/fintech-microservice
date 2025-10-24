package br.rafael.client.api.application.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {

    private Long id;
    private String name;
    private Integer age;
    private String cpf;
    
}