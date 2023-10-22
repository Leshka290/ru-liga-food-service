package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.CreateOrUpdateItemDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.RestaurantMenuItem;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMenuItemMapper {
    @Mapping(target = "image", expression = "java(restaurantMenuItem.getImage() != null ? restaurantMenuItem.getImage().getUrl() : \"\")")
    List<RestaurantMenuItemDto> restaurantMenuItemsToRestaurantMenuItemsDto(List<RestaurantMenuItem> restaurantMenuItems);

    @Mapping(target = "image", expression = "java(restaurantMenuItem.getImage() != null ? restaurantMenuItem.getImage().getUrl() : \"\")")
    RestaurantMenuItemDto restaurantMenuItemToRestaurantMenuItemDto(RestaurantMenuItem restaurantMenuItem);

}