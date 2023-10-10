package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.MenuItems;
import ru.liga.dto.OrderDto;
import ru.liga.dto.OrderStatus;
import ru.liga.entity.Order;
import ru.liga.mapper.OrderMapper;
import ru.liga.repository.OrderRepository;
import ru.liga.service.OrderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto getOrderById(Long id) {
        log.info("Current method is - getOrderById");
        Order order = orderRepository.getOrderById(id).orElseThrow();

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        log.info("Current method is - getAllOrders");
        List<Order> orders = orderRepository.findAll();
        return orderMapper.ordersToOrderDto(orders);
    }

    @Override
    public CreatedOrderDto createOrder(Long restaurantId, MenuItems menuItems) {
        return null;
    }

    @Override
    public List<OrderDto> getAllByStatus(OrderStatus status) {
        return null;
    }
}
