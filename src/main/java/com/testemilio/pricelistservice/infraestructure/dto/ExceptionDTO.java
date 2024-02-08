package com.testemilio.pricelistservice.infraestructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {
    @Schema(description = "Http Status code",
            example = "400")
    private Integer code;

    @Schema(description = "Error description",
            example = "Invalid Date Format")
    private String description;

}
