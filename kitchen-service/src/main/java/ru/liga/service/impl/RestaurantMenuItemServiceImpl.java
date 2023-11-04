package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.dto.CreateOrUpdateItemDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.dto.UpdatePriceMenuItemDto;
import ru.liga.entity.Image;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.ItemNotFoundException;
import ru.liga.exception.RestaurantMenuItemNotFoundException;
import ru.liga.mapper.RestaurantMenuItemMapper;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.ImageService;
import ru.liga.service.RestaurantMenuItemService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantMenuItemServiceImpl implements RestaurantMenuItemService {
    private final RestaurantMenuItemRepository restaurantMenuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final ImageService imageService;
    private final RestaurantMenuItemMapper restaurantMenuItemMapper;

    @Override
    public List<RestaurantMenuItemDto> getRestaurantMenuItemsByRestaurant(RestaurantDto restaurantDto) {
        log.info("started getRestaurantMenuItemsByRestaurant.");
        List<RestaurantMenuItem> restaurantMenuItems = restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurant_Name(restaurantDto.getName());
        return restaurantMenuItemMapper.restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems);
    }

    @Override
    public List<RestaurantMenuItemDto> getRestaurantMenuItemsByRestaurantId(Long id) {
        log.info("started getRestaurantMenuItemsByRestaurantId.");
        List<RestaurantMenuItem> restaurantMenuItems = restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurantId(id);
        return restaurantMenuItemMapper.restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems);
    }

    @Override
    public RestaurantMenuItemDto createRestaurantMenuItem(String restaurantName,
                                                          CreateOrUpdateItemDto createOrUpdateItemDto,
                                                          MultipartFile imageFile) {
        log.info("started createRestaurantMenuItem.");
        restaurantMenuItemRepository.save(createMenuItem(restaurantName, createOrUpdateItemDto, imageFile));
        return restaurantMenuItemMapper.restaurantMenuItemToRestaurantMenuItemDto(
                createMenuItem(restaurantName, createOrUpdateItemDto, imageFile));
    }

    private RestaurantMenuItem createMenuItem(String restaurantName,
                                              CreateOrUpdateItemDto createOrUpdateItemDto,
                                              MultipartFile imageFile) {
        log.info("started createMenuItem.");
        Restaurant restaurant = restaurantRepository.getRestaurantByName(restaurantName).orElseThrow();

        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        restaurantMenuItem.setName(createOrUpdateItemDto.getName());
        restaurantMenuItem.setDescription(createOrUpdateItemDto.getDescription());
        restaurantMenuItem.setPrice(createOrUpdateItemDto.getPrice());
        restaurantMenuItem.setRestaurant(restaurant);

        restaurantMenuItem.setImage(saveImage(imageFile));
        return restaurantMenuItem;
    }

    @Override
    public void updatePriceMenuItem(UpdatePriceMenuItemDto updatePriceMenuItemDto) {
        log.info("started updatePriceMenuItem.");
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository
                .findById(updatePriceMenuItemDto.getIdMenuItem())
                .orElseThrow(RestaurantMenuItemNotFoundException::new);

        restaurantMenuItem.setPrice(updatePriceMenuItemDto.getPrice());
        restaurantMenuItemRepository.save(restaurantMenuItem);
    }

    @Override
    public boolean updateImage(Long id, MultipartFile imageFile) {
        log.info("started updateImage.");
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository
                .findById(id).orElseThrow(ItemNotFoundException::new);

        restaurantMenuItem.setImage(saveImage(imageFile));
        restaurantMenuItemRepository.save(restaurantMenuItem);
        return true;
    }

    private Image saveImage(MultipartFile imageFile) {
        Image image;
        try {
            image = imageService.saveImageFile(imageFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return image;
    }
}
