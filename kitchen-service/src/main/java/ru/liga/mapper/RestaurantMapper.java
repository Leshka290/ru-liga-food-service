package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    List<RestaurantDto> restaurantsToRestaurantsDto(List<Restaurant> restaurantsDto);
}
