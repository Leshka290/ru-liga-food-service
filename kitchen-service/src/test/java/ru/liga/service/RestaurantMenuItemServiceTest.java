package ru.liga.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.dto.CreateOrUpdateItemDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.dto.UpdatePriceMenuItemDto;
import ru.liga.entity.Image;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.RestaurantMenuItemNotFoundException;
import ru.liga.mapper.RestaurantMenuItemMapper;
import ru.liga.repository.ImageRepository;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.impl.ImageServiceImpl;
import ru.liga.service.impl.RestaurantMenuItemServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantMenuItemServiceTest {
    @Mock
    private RestaurantMenuItemRepository restaurantMenuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ImageRepository imageRepository;


    @Mock
    private RestaurantMenuItemMapper restaurantMenuItemMapper;

    @InjectMocks
    private ImageServiceImpl imageService;

    @InjectMocks
    private RestaurantMenuItemServiceImpl restaurantMenuItemService;

    @Test
    public void getRestaurantMenuItemsByRestaurantTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName("name");
        List<RestaurantMenuItem> restaurantMenuItems = List.of(new RestaurantMenuItem(), new RestaurantMenuItem());
        List<RestaurantMenuItemDto> restaurantMenuItemsDto = List.of(new RestaurantMenuItemDto(), new RestaurantMenuItemDto());

        when(restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurant_Name(restaurantDto.getName())).thenReturn(restaurantMenuItems);
        when(restaurantMenuItemMapper
                .restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems)).thenReturn(restaurantMenuItemsDto);

        assertNotNull(restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurant_Name(restaurantDto.getName()));
        assertEquals(restaurantMenuItemMapper.restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems),
                restaurantMenuItemsDto);
        assertEquals(restaurantMenuItemService.getRestaurantMenuItemsByRestaurant(restaurantDto), restaurantMenuItemsDto);
    }

    @Test
    public void getRestaurantMenuItemsByRestaurantIdTest() {
        long id = 1;
        List<RestaurantMenuItem> restaurantMenuItems = List.of(new RestaurantMenuItem(), new RestaurantMenuItem());
        List<RestaurantMenuItemDto> restaurantMenuItemsDto = List.of(new RestaurantMenuItemDto(), new RestaurantMenuItemDto());

        when(restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurantId(id)).thenReturn(restaurantMenuItems);
        when(restaurantMenuItemMapper
                .restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems)).thenReturn(restaurantMenuItemsDto);

        assertNotNull(restaurantMenuItemRepository
                .getRestaurantMenuItemsByRestaurantId(id));
        assertEquals(restaurantMenuItemMapper.restaurantMenuItemsToRestaurantMenuItemsDto(restaurantMenuItems),
                restaurantMenuItemsDto);
        assertEquals(restaurantMenuItemService.getRestaurantMenuItemsByRestaurantId(id), restaurantMenuItemsDto);
    }

    @Test
    public void createRestaurantMenuItemTest() throws IOException {
        String restaurantName = "restaurantName";
         MultipartFile imageFile = new MockMultipartFile("test.jpg", "test.jpg",
                "image/jpeg", "test data".getBytes());

        Restaurant restaurant = new Restaurant();
        restaurant.setName("restaurantName");
        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        RestaurantMenuItemDto restaurantMenuItemDto = new RestaurantMenuItemDto();

        Optional<Restaurant> restaurantOptional = restaurantRepository.getRestaurantByName("restaurantName");

        when(imageRepository.findByFilePath(anyString())).thenReturn(Optional.empty());
        when(restaurantRepository.getRestaurantByName(restaurantName)).thenReturn(restaurantOptional);
        when(restaurantMenuItemRepository.save(restaurantMenuItem)).thenReturn(restaurantMenuItem);
        when(restaurantMenuItemMapper.restaurantMenuItemToRestaurantMenuItemDto(restaurantMenuItem))
                .thenReturn(restaurantMenuItemDto);
        imageService.saveImageFile(imageFile);

        verify(imageRepository, times(1)).saveAndFlush(any());
        assertEquals(restaurantRepository.getRestaurantByName(restaurantName), restaurantOptional);
        assertEquals(restaurantMenuItemRepository.save(restaurantMenuItem), restaurantMenuItem);
        assertEquals(restaurantMenuItemMapper.restaurantMenuItemToRestaurantMenuItemDto(restaurantMenuItem),
                restaurantMenuItemDto);
    }

    @Test
    public void updatePriceMenuItemTest() {
        long id = 1;
        int price = 500;
        UpdatePriceMenuItemDto updatePriceMenuItemDto = new UpdatePriceMenuItemDto();
        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        updatePriceMenuItemDto.setIdMenuItem(id);

        when(restaurantMenuItemRepository.findById(id)).thenReturn(Optional.of(restaurantMenuItem));

        restaurantMenuItem.setPrice(price);
        when(restaurantMenuItemRepository.save(restaurantMenuItem)).thenReturn(restaurantMenuItem);

        assertEquals(restaurantMenuItemRepository.findById(id), Optional.of(restaurantMenuItem));
        assertEquals(restaurantMenuItemRepository.save(restaurantMenuItem).getPrice(), price);
    }
}