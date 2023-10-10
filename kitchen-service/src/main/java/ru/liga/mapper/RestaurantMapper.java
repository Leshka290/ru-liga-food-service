package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(source = "name", target = "name")
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
}
