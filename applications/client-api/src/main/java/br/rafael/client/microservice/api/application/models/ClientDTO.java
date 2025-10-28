package br.rafael.client.microservice.api.application.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Client", description = "Representation of a client")
public class ClientDTO {

    @Schema(description = "Database identifier", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Full name of the client", example = "Rafael Silva")
    private String name;

    @Min(0)
    @Schema(description = "Client age in years", example = "30")
    private Integer age;

    @NotBlank
    @Schema(description = "Brazilian CPF (only digits)", example = "12345678901")
    private String cpf;
}
