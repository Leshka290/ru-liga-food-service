package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.liga.entity.Restaurant;

import java.util.List;

@FeignClient(name = "kitchen-service", url = "http://localhost:8090")
public interface KitchenFeign {
    @GetMapping("/restaurant/all")
    List<Restaurant> getRestaurants();
}
