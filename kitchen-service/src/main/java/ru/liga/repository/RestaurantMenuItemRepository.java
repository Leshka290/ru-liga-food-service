package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.RestaurantMenuItem;

import java.util.List;

public interface RestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem, Long> {
    List<RestaurantMenuItem> getRestaurantMenuItemsByRestaurant_Name(String restaurantName);

    List<RestaurantMenuItem> getRestaurantMenuItemsByRestaurantId(Long id);
}
