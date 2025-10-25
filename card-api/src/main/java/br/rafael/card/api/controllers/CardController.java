package br.rafael.card.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.card.api.application.models.CardDTO;
import br.rafael.card.api.application.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
@Log4j2
public class CardController {
    
    private final CardService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String status() {
        log.info("Server is running fine.");
        return "Ok";
    }

    @GetMapping(params = "income")
    public ResponseEntity<?> getMethodName(@RequestParam Long income) {
        return ResponseEntity.ok(service.findByIncome(income));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> findByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody CardDTO cardDto) {
        service.create(cardDto);
    }
}
