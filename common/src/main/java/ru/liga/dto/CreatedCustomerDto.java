package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CreatedCustomers")
public class CreatedCustomerDto {
    private String name;
    private AddressDto address;
}
