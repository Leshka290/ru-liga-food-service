package ru.liga.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.liga.client.KitchenFeign;
import ru.liga.client.OrderFeign;
import ru.liga.dto.OrderStatus;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/receiver")
@RestController
public class ReceiverController {
    private final KitchenFeign kitchenFeign;
    private final OrderFeign orderFeign;

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

    @GetMapping("/order/{id}")
    public Optional<Order> getOrderById(Long id) {
        log.info("Request GET order by id");

        return orderFeign.getOrderById(id);
    }

    @PostMapping("/update/{id}")
    void updateOrderStatusById(@PathVariable Long id, @RequestParam OrderStatus status) {
        log.info("Request PATCH order status by id");
        orderFeign.updateOrderStatusById(id, status);
    }
}
