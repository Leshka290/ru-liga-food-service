package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "orderId")
public class OrderIdDto {
    private Long id;
}
