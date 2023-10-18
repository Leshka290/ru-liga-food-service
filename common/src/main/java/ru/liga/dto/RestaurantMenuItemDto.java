package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RestaurantMenuItem")
public class RestaurantMenuItemDto {
    private String name;
    private Integer price;
    private String description;
    private RestaurantDto restaurant;
    private String image;
}
