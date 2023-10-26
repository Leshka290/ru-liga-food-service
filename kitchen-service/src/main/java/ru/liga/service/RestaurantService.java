package ru.liga.service;

import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurant> getRestaurants();

    RestaurantDto getRestaurantById(Long id);
}
