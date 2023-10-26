package ru.liga.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.client.KitchenFeign;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.Restaurant;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/receiver")
@RestController
public class ReceiverController {
    private final KitchenFeign kitchenFeign;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        log.info("Request GET restaurants");

        return kitchenFeign.getRestaurants();
    }

    @GetMapping("/menu_items")
    public List<RestaurantMenuItemDto> getMenuItemsByRestaurantId(Long id) {
        log.info("Request GET menu items restaurant");

        return kitchenFeign.getMenuItemsByRestaurantId(id);
    }
}
