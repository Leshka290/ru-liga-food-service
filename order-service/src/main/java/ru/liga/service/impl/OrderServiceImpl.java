package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.liga.dto.*;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.mapper.OrderMapper;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.service.OrderService;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;

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
    public CreatedOrderDto createOrder(CustomerDto customerDto, Long restaurantId, MenuItems menuItems) {
        CreatedOrderDto createdOrderDto = new CreatedOrderDto();
        createdOrderDto.setSecretPaymentUrl(createUrlForOrderPayment());
        //Вызов ресторана получение координат, сохранение в заказе, получение меню,
        //Вызов Свободного курьера, получение координат
        //Вычисление времени доставки
        createdOrderDto.setEstimatedTimeOfArrival("30");
        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 0; i < menuItems.getQuantity(); i++) {
            orderItems.add(new OrderItem());
        }

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setDistance(customerDto.getDistance());
        customer.setId(5);

        Order order = new Order();
        order.setStatus(OrderStatus.ACTIVE);
        order.setRestaurant(null);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        order.setItems(orderItems);
        order.setCustomer(customer);
        orderRepository.save(order);

        return createdOrderDto;
    }

    private String createUrlForOrderPayment() {
        String protocol = "http";
        String host = "localhost";
        int port = 8080;
        String path = "/payment/" + generateRandomInt();
        try {
            URL url = new URL(protocol, host, port, path);
            return url.toString();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private int generateRandomInt(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = random.generateSeed(200);
        random.nextBytes(bytes);
        return random.nextInt(Integer.MAX_VALUE);
    }
    @Override
    public List<OrderDto> getAllByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.getOrdersByStatus(status);
        return orderMapper.ordersToOrderDto(orders);
    }
}
