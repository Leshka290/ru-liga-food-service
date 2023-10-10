package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Delivery")
public class DeliveryDto {
    private long order_id;
    private String restaurant;
    private String customer;
    private String payment;
}
