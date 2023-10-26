package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;
import ru.liga.exception.RestaurantNotFoundException;
import ru.liga.mapper.RestaurantMapper;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.RestaurantService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);

        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }
}
