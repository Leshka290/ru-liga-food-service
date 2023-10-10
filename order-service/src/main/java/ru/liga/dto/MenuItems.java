package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "MenuItems")
public class MenuItems {
    private int quantity;
    private int menu_item_id;
}
