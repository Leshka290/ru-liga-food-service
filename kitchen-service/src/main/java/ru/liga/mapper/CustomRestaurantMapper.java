package ru.liga.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreatedRestaurantDto;
import ru.liga.entity.Address;
import ru.liga.entity.Restaurant;

@Component
public class CustomRestaurantMapper {

    public CreatedRestaurantDto restaurantTocreatedRestaurantDto(Restaurant restaurant) {
        Address address = restaurant.getAddress();
        String addressStr = String.format("%s, %s, %s", address.getCity(), address.getStreet(), address.getBuilding());

        CreatedRestaurantDto createdRestaurantDto = new CreatedRestaurantDto();
        createdRestaurantDto.setName(restaurant.getName());
        createdRestaurantDto.setAddress(addressStr);
        return createdRestaurantDto;
    }
}
