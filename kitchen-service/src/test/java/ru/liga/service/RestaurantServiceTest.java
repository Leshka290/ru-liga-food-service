package ru.liga.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.dto.AddressDto;
import ru.liga.dto.CreatedRestaurantDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Address;
import ru.liga.entity.Restaurant;
import ru.liga.mapper.CustomAddressMapper;
import ru.liga.mapper.RestaurantMapperImpl;
import ru.liga.repository.AddressRepository;
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
    private AddressRepository addressRepository;

    @Mock
    private RestaurantMapperImpl restaurantMapper;

    @Mock
    private CustomAddressMapper addressMapper;

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

    @Test
    public void createRestaurantTest() {
        Restaurant restaurant = new Restaurant();
        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");
        address.setBuilding("building");
        restaurant.setAddress(address);
        restaurant.setName("name");

        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("city, street, building");

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName("name");

        CreatedRestaurantDto createdRestaurantDto = new CreatedRestaurantDto();
        createdRestaurantDto.setName("name");
        createdRestaurantDto.setAddress(addressDto.getAddress());

        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.addressToAddressDto(createdRestaurantDto.getAddress())).thenReturn(address);
        when(restaurantMapper.restaurantToRestaurantDto(restaurant)).thenReturn(restaurantDto);

        assertEquals(restaurantRepository.save(restaurant), restaurant);
        assertEquals(addressRepository.save(address), address);
        assertEquals(addressMapper.addressToAddressDto(createdRestaurantDto.getAddress()), address);
        assertEquals(restaurant.getAddress(), address);
        assertEquals(restaurantMapper.restaurantToRestaurantDto(restaurant), restaurantDto);
        assertEquals(restaurantService.createRestaurant(createdRestaurantDto), restaurantDto);
        assertEquals(restaurant.getName(), "name");
    }
}
