package br.rafael.card.api.controllers;

import br.rafael.card.api.application.models.CardDTO;
import br.rafael.card.api.application.models.ClientCardDTO;
import br.rafael.card.api.application.services.CardService;
import br.rafael.card.api.application.services.ClientCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Card Controller", description = "Operations for managing cards and client-card relations")
public class CardController {

    private final CardService cardService;
    private final ClientCardService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Health check", description = "Simple endpoint to check if the service is running")
    public String status() {
        log.info("Server is running fine.");
        return "Ok";
    }

    @GetMapping(params = "income")
    @Operation(
        summary = "Find cards by income",
        description = "Returns a list of cards available for the given income threshold."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "List of available cards",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CardDTO.class))
            )
        ),
        @ApiResponse(responseCode = "400", description = "Invalid income parameter", content = @Content)
    })
    public ResponseEntity<List<CardDTO>> findByIncome(
        @Parameter(in = ParameterIn.QUERY, required = true, description = "Client income limit to filter eligible cards")
        @RequestParam Long income) {
        return ResponseEntity.ok(cardService.findByIncome(income));
    }

    @GetMapping(params = "cpf")
    @Operation(
        summary = "Find client cards by CPF",
        description = "Returns all cards that belong to a client identified by CPF."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "List of client's cards",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ClientCardDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    public ResponseEntity<List<ClientCardDTO>> findByCpf(
        @Parameter(in = ParameterIn.QUERY, required = true, description = "Client CPF (only digits)")
        @RequestParam String cpf) {
        return ResponseEntity.ok(clientService.findByCpf(cpf));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Register new card",
        description = "Creates a new card and persists it in the database."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Card successfully created"),
        @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    })
    public void register(@Valid @RequestBody CardDTO cardDto) {
        cardService.create(cardDto);
    }
}
