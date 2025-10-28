package br.rafael.card.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ClientCard", description = "Representation of a card owned by a client")
public class ClientCardDTO {

    @Schema(description = "Card name", example = "Gold Credit")
    private String name;

    @Schema(description = "Card brand", example = "Mastercard")
    private String brand;

    @Schema(description = "Approved limit for this client", example = "7500.00")
    private BigDecimal approvedLimit;
}
