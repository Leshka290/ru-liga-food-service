package ru.liga.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.mapper.RestaurantMenuItemMapper;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.service.impl.RestaurantMenuItemServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantMenuItemServiceTest {
    @Mock
    private RestaurantMenuItemRepository restaurantMenuItemRepository;

    @Mock
    private RestaurantMenuItemMapper restaurantMenuItemMapper;

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
}
