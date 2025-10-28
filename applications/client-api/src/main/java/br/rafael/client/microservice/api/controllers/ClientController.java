package br.rafael.client.microservice.api.controllers;

import br.rafael.client.microservice.api.application.models.ClientDTO;
import br.rafael.client.microservice.api.application.services.ClientService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/clients")
@Log4j2
@RequiredArgsConstructor
@Tag(name = "Client Controller", description = "Operations related to clients")
public class ClientController {

    private final ClientService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        log.info("Server is running fine");
        return "Ok";
    }

    @GetMapping(params = "cpf")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find client by CPF", description = "Returns client data when CPF exists in DB")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Client found",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ClientDTO.class))),
        @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ClientDTO findByCpf(
        @Parameter(in = ParameterIn.QUERY, name = "cpf", required = true, description = "Client CPF")
        @RequestParam String cpf) {
        return service.findByCpf(cpf);
    }

    @PostMapping
    @Operation(summary = "Create client", description = "Register a new client")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Client created"),
        @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    })
    public ResponseEntity<Void> createClient(@Valid @RequestBody ClientDTO clientDTO) {

        service.create(clientDTO);

        var uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("cpf={cpf}")
            .buildAndExpand(clientDTO.getCpf())
            .toUri();

        return ResponseEntity.created(uri).build();
    }
}
