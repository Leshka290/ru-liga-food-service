package ru.liga.service;

import ru.liga.dto.*;
import ru.liga.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDto getOrderDtoById(Long id);

    Order getOrderById(Long id);

    List<OrderDto> getAllOrders();

    CreatedOrderDto createOrder(CreatedCustomerDto customerDto, Long restaurantId, MenuItems menuItems);

    List<OrderDto> getAllByStatus(OrderStatus status);

    void updateOrderStatusById(Long id, OrderStatus status);
}

