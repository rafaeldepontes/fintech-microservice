package br.rafael.card.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Card", description = "Representation of a credit card")
public class CardDTO {

    @Schema(description = "Card ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Card name", example = "Gold Credit")
    private String name;

    @NotBlank
    @Schema(description = "Card brand", example = "VISA")
    private String brand;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Minimum income required for the card", example = "3500.00")
    private BigDecimal income;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Credit limit assigned to the card", example = "10000.00")
    private BigDecimal limit;
}
