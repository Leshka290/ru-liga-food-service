package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreatedRestaurantDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Address;
import ru.liga.entity.Restaurant;
import ru.liga.exception.RestaurantNotFoundException;
import ru.liga.mapper.RestaurantMapper;
import ru.liga.repository.AddressRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
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

    @Override
    public RestaurantDto createRestaurant(CreatedRestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(getAndSaveAddress(restaurantDto.getAddress()));
        restaurantRepository.save(restaurant);

        return restaurantMapper.restaurantToRestaurantDto(restaurant);
    }

    private Address getAndSaveAddress(String addressStr) {
        Address address = new Address();
        List<String> strings = Stream.of(addressStr.split(","))
                .map(String::new)
                .collect(Collectors.toList());

        address.setCity(strings.get(0));
        address.setStreet(strings.get(1));
        address.setBuilding(strings.get(2));
        addressRepository.save(address);
        return address;
    }
}
