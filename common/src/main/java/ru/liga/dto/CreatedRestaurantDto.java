package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CreatedRestaurants")
public class CreatedRestaurantDto {
    private String name;
    private String address;
}
