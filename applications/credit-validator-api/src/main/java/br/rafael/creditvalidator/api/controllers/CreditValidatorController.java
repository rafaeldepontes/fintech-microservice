package br.rafael.creditvalidator.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.rafael.creditvalidator.api.application.models.ClientCardRequest;
import br.rafael.creditvalidator.api.application.models.ClientStatusInfo;
import br.rafael.creditvalidator.api.application.services.CreditValidatorService;
import br.rafael.creditvalidator.domain.ClientStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/credit-validate")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Credit Validator Controller", description = "Operations related to credit cards")
public class CreditValidatorController {
    
    private final CreditValidatorService service;

    @GetMapping
    public String healthCheck() {
        log.info("[INFO] Server is running fine");
        return "Ok";
    }
    
    @GetMapping(params = "cpf")
    @Operation(summary = "Find client and cards by their owner's CPF", description = "Returns client and cards data when CPF exists in DB")
    @ApiResponse(responseCode = "200", description = "Client and Cards found",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ClientStatus.class)))
    public ResponseEntity<?> checksClientStatusByCpf(
        @Parameter(in = ParameterIn.QUERY, name = "cpf", required = true, description = "Client CPF")
        @RequestParam String cpf) {
        return ResponseEntity.ok(service.checkClientStatus(cpf));
    }

    @PostMapping
    @Operation(summary = "Checks available cards", description = "Checks available cards for a cpf based on their income")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cards found"),
        @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    })
    public ResponseEntity<?> checksCardsByCpfAndIncome(@Valid @RequestBody ClientStatusInfo clientStatusInfo) {
        return ResponseEntity.ok(service.findAllCardsAvailable(clientStatusInfo));
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Link a existing card to a client", description = "Takes a existing card and link it to a client")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Card Linked"),
        @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    })
    public Object registerCardToClient(@Valid @RequestBody ClientCardRequest clientCardRequest) throws JsonProcessingException {
        return service.sendCardRequest(clientCardRequest);
    }
    
    

}
