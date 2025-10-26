package br.rafael.creditvalidator.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.rafael.creditvalidator.domain.Card;
import br.rafael.creditvalidator.domain.CardDTO;

@FeignClient(value = "card-api", path = "/api/v1/cards")
public interface CardClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<Card>> findByCpf(@RequestParam String cpf);

    @GetMapping(params = "income")
    public ResponseEntity<List<CardDTO>> findByIncome(@RequestParam Long income);

}