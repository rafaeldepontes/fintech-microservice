package br.rafael.card.domain;

import lombok.Data;

@Data
public class Client {

    private Long id;
    private String name;
    private Integer age;
    private String cpf;

}
