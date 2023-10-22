package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;

import java.util.List;

@FeignClient(name = "kitchen-service", url = "http://localhost:8090")
public interface KitchenFeign {
    @GetMapping("/restaurant/all")
    List<RestaurantDto> getRestaurants();
}
