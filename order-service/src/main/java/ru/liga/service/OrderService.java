package ru.liga.service;

import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.MenuItems;
import ru.liga.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();
    CreatedOrderDto createOrder(Long restaurantId, MenuItems menuItems);
}
