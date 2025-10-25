package br.rafael.creditvalidator.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.rafael.creditvalidator.api.application.models.ClientCardRequest;
import br.rafael.creditvalidator.api.application.models.ClientStatusInfo;
import br.rafael.creditvalidator.api.application.services.CreditValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/credit-validate")
@RequiredArgsConstructor
@Log4j2
public class CreditValidatorController {
    
    private final CreditValidatorService service;

    @GetMapping
    public String healthCheck() {
        log.info("[INFO] Server is running fine");
        return "Ok";
    }
    
    @GetMapping(params = "cpf")
    public ResponseEntity<?> checksClientStatusByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(service.checkClientStatus(cpf));
    }

    @PostMapping
    public ResponseEntity<?> checksCardsByCpfAndIncome(@RequestBody ClientStatusInfo clientStatusInfo) {
        return ResponseEntity.ok(service.findAllCardsAvailable(clientStatusInfo));
    }

    // @PostMapping("/card")
    // public String registerCardToClient(@RequestBody ClientCardRequest clientCardRequest) {
        
    //     return entity;
    // }
    
    

}
