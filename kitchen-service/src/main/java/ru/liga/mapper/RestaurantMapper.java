package ru.liga.mapper;

import org.mapstruct.Mapper;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
}