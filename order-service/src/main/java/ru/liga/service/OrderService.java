package ru.liga.service;

import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.MenuItems;
import ru.liga.dto.OrderDto;
import ru.liga.dto.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    CreatedOrderDto createOrder(Long restaurantId, MenuItems menuItems);

    List<OrderDto> getAllByStatus(OrderStatus status);
}

