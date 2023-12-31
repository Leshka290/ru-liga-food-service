package ru.liga.service;

import ru.liga.dto.CourierDto;
import ru.liga.dto.CourierStatus;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;

import java.util.List;

public interface CourierService {
    CourierDto addCourier(CourierDto courierDto);

    List<CourierDto> getAllByStatus(CourierStatus status);

    CourierDto updateStatus(Long id, CourierStatus status);

    List<Restaurant> getRestaurants();
}
