package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Delivery")
public class DeliveryDto {
    private long order_id;
    private RestaurantDto restaurant;
    private CustomerDto customer;
    private PaymentDto payment;
}
