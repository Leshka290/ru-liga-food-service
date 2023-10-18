package ru.liga.service;

import org.springframework.web.multipart.MultipartFile;
import ru.liga.dto.CreateOrUpdateItemDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.dto.UpdatePriceMenuItemDto;

import java.util.List;

public interface RestaurantService {
    List<RestaurantMenuItemDto> getRestaurantMenuItemsByRestaurant(RestaurantDto restaurantDto);

    RestaurantMenuItemDto createRestaurantMenuItem(CreateOrUpdateItemDto createOrUpdateItemDto, MultipartFile imageFile);

    void updatePriceMenuItem(UpdatePriceMenuItemDto updatePriceMenuItemDto);

    boolean updateImage(Long id, MultipartFile imageFile);
}
