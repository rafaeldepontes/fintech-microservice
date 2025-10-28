package br.rafael.creditvalidator.api.application.models;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "Client Card", description = "Representation of a valid Card and Client body")
public class ClientCardRequest {
    
    @Schema(description = "Database Card's identifier", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long cardId;

    @NotBlank
    @Schema(description = "Brazilian CPF (only digits)", example = "12345678901")
    private String cpf;

    @NotBlank
    @Schema(description = "Street Address", example = "Street, City, Country")
    private String address;

    @NotNull
    @Min(value = 0)
    @Schema(description = "Client's income", example = "1000.00")
    private BigDecimal approvedLimit;

}
