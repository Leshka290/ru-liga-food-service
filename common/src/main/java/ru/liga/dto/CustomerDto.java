package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Customers")
public class CustomerDto {
    private String name;
    private int distance;
}
