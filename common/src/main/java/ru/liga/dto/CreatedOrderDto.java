package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CreatedOrders")
public class CreatedOrderDto {
    private Long id;
    private String secret_payment_url;
    private String estimated_time_of_arrival;
}
