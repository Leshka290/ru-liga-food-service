package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UpdatePriceMenuItem")
public class UpdatePriceMenuItemDto {
    private Long idMenuItem;
    private Integer price;
}
