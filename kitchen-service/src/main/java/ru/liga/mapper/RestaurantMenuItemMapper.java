package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.RestaurantMenuItem;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMenuItemMapper {
    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(source = "restaurantMenuItem.image", target = "image")
    List<RestaurantMenuItemDto> restaurantMenuItemsToRestaurantMenuItemsDto(List<RestaurantMenuItem> restaurantMenuItems);

    @Mapping(source = "restaurantMenuItemDto.name", target = "name")
    @Mapping(source = "restaurantMenuItemDto.price", target = "price")
    @Mapping(source = "restaurantMenuItemDto.description", target = "description")
    @Mapping(source = "restaurantMenuItemDto.restaurant", target = "restaurant")
    RestaurantMenuItem restaurantMenuItemDtoToRestaurantMenuItem(RestaurantMenuItemDto restaurantMenuItemDto);
}