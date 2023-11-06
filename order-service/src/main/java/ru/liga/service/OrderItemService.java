package ru.liga.service;

import ru.liga.dto.MenuItems;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> createOrderItems(Restaurant restaurant, MenuItems menuItems);
}
