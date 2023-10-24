package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.client.KitchenFeign;
import ru.liga.entity.Restaurant;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reciever")
@RestController
public class ReceiverController {
    private final KitchenFeign kitchenFeign;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        log.info("Request GET restaurants");

        return kitchenFeign.getRestaurants();
    }
}
