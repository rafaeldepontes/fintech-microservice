package br.rafael.creditvalidator.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/v1/credit-validate")
@RequiredArgsConstructor
@Log4j2
public class CreditValidatorController {
    
    @GetMapping
    public String healthCheck() {
        log.info("[INFO] Server is running fine");
        return "Ok";
    }
    

}
