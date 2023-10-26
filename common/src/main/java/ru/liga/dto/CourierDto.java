package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Couriers")
public class CourierDto {
    private String name;
    private String phone;
    private int coordinates;
    private String status;
}
