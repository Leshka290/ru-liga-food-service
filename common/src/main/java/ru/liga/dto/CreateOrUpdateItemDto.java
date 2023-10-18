package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateOrUpdateItem")
public class CreateOrUpdateItemDto {
    private String description;
    private int price;
    private String name;
    private String title;
    private RestaurantDto restaurant;
}
