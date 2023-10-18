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
    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(target = "image", expression = "java(restaurantMenuItem.getImage() != null ? restaurantMenuItem.getImage().getUrl() : \"\")")
    List<RestaurantMenuItemDto> restaurantMenuItemsToRestaurantMenuItemsDto(List<RestaurantMenuItem> restaurantMenuItems);

    RestaurantMenuItem createOrUpdateItemDtoToRestaurantMenuItem(CreateOrUpdateItemDto createOrUpdateItemDto);

    @Mapping(source = "restaurantMenuItem.name", target = "name")
    @Mapping(source = "restaurantMenuItem.price", target = "price")
    @Mapping(source = "restaurantMenuItem.description", target = "description")
    @Mapping(target = "image", expression = "java(restaurantMenuItem.getImage() != null ? restaurantMenuItem.getImage().getUrl() : \"\")")
    RestaurantMenuItemDto restaurantMenuItemToRestaurantMenuItemDto(RestaurantMenuItem restaurantMenuItem);
}