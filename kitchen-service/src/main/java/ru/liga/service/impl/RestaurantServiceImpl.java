package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.dto.UpdatePriceMenuItemDto;
import ru.liga.entity.Image;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapper.RestaurantMenuItemMapper;
import ru.liga.repository.ImageRepository;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.service.ImageService;
import ru.liga.service.RestaurantService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final RestaurantMenuItemMapper restaurantMenuItemMapper;

    @Override
    public List<RestaurantMenuItemDto> getRestaurantMenuItemsByRestaurant(RestaurantDto restaurantDto) {
        List<RestaurantMenuItem> restaurantMenuItems = restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurant_Name(restaurantDto.getName());

        return restaurantMenuItemMapper.restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems);
    }

    @Override
    public RestaurantMenuItemDto createRestaurantMenuItem(RestaurantMenuItemDto restaurantMenuItemDto, MultipartFile imageFile) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemMapper
                .restaurantMenuItemDtoToRestaurantMenuItem(restaurantMenuItemDto);

        Image image;
        try {
            image = imageService.saveImageFile(imageFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        imageRepository.save(image);
        restaurantMenuItemRepository.save(restaurantMenuItem);
        return restaurantMenuItemDto;
    }

    @Override
    public void updatePriceMenuItem(UpdatePriceMenuItemDto updatePriceMenuItemDto) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository
                .findById(updatePriceMenuItemDto.getId_menu_item()).orElseThrow();
        restaurantMenuItem.setPrice(updatePriceMenuItemDto.getPrice());
        restaurantMenuItemRepository.save(restaurantMenuItem);
    }
}
