package ru.liga.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;
import ru.liga.mapper.CustomRestaurantMapper;
import ru.liga.mapper.RestaurantMapperImpl;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.impl.RestaurantServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapperImpl restaurantMapper;

    @Mock
    private CustomRestaurantMapper customRestaurantMapper;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    public void getRestaurantsTest() {
        List<Restaurant> restaurants = List.of(new Restaurant(), new Restaurant());
        when(restaurantRepository.findAll()).thenReturn(restaurants);

        assertNotNull(restaurantRepository.findAll());
        assertEquals(restaurantRepository.findAll().size(), restaurants.size());
    }

    @Test
    public void getRestaurantByIdTest() {
        long id = 1;
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));
        when(restaurantMapper.restaurantToRestaurantDto(restaurant)).thenReturn(new RestaurantDto());

        RestaurantDto restaurantDto = restaurantService.getRestaurantById(id);
        assertNotNull(restaurantDto);
    }
}
