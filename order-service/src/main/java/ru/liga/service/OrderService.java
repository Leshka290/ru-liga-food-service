package ru.liga.service;

import ru.liga.dto.*;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Long id);

    List<OrderDto> getAllOrders();

    CreatedOrderDto createOrder(CustomerDto customerDto, Long restaurantId, MenuItems menuItems);

    List<OrderDto> getAllByStatus(OrderStatus status);
}

