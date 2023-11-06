package ru.liga.service;

import ru.liga.dto.CreatedRestaurantDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getRestaurants();

    RestaurantDto getRestaurantById(Long id);

    RestaurantDto createRestaurant(CreatedRestaurantDto restaurantDto);
}
