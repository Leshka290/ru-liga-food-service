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
    @Mapping(source = "restaurantMenuItems.id", target = "id")
    @Mapping(target = "image", expression = "java(restaurantMenuItem.getImage() != null ? restaurantMenuItem.getImage().getUrl() : \"\")")
    List<RestaurantMenuItemDto> restaurantMenuItemsToRestaurantMenuItemsDto(List<RestaurantMenuItem> restaurantMenuItems);

    @Mapping(target = "image", expression = "java(restaurantMenuItem.getImage() != null ? restaurantMenuItem.getImage().getUrl() : \"\")")
    RestaurantMenuItemDto restaurantMenuItemToRestaurantMenuItemDto(RestaurantMenuItem restaurantMenuItem);

    @Mapping(target = "image", expression = "java(new ru.liga.entity.Image())")
    RestaurantMenuItem restaurantMenuItemDtoToRestaurantMenuItem(RestaurantMenuItemDto restaurantMenuItemDto);

}