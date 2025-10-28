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
@Schema(name = "Client Status", description = "Representation of a valid Client and their Income")
public class ClientStatusInfo {

    @NotBlank
    @Schema(description = "Brazilian CPF (only digits)", example = "12345678901")
    private String cpf;

    @NotNull
    @Min(value = 0)
    @Schema(description = "Client's income", example = "1000.00")
    private BigDecimal income;

}
