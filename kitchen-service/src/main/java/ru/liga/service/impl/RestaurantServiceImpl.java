package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreatedRestaurantDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Address;
import ru.liga.entity.Restaurant;
import ru.liga.exception.RestaurantNotFoundException;
import ru.liga.mapper.CustomAddressMapper;
import ru.liga.mapper.RestaurantMapper;
import ru.liga.repository.AddressRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.RestaurantService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final RestaurantMapper restaurantMapper;
    private final CustomAddressMapper addressMapper;

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(RestaurantNotFoundException::new);

        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public RestaurantDto createRestaurant(CreatedRestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        Address address = addressMapper.addressToAddressDto(restaurantDto.getAddress());

        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(address);
        addressRepository.save(address);
        restaurantRepository.save(restaurant);

        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

}
