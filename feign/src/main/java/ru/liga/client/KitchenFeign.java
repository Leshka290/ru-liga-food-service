package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.Restaurant;

import java.util.List;

@FeignClient(name = "kitchen-service", url = "http://localhost:8090")
public interface KitchenFeign {
    @GetMapping("/restaurant/all")
    List<Restaurant> getRestaurants();

    @GetMapping(value = "/menu_item/all/{id}")
    List<RestaurantMenuItemDto> getMenuItemsByRestaurantId(@PathVariable Long id);
}
