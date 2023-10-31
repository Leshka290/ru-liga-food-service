package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.client.KitchenFeign;
import ru.liga.dto.*;
import ru.liga.entity.*;
import ru.liga.exception.OrderNotFoundException;
import ru.liga.mapper.OrderMapper;
import ru.liga.repository.AddressRepository;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.service.OrderItemService;
import ru.liga.service.OrderService;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final OrderItemService orderItemService;
    private final OrderMapper orderMapper;
    private final KitchenFeign kitchenFeign;

    @Override
    public OrderDto getOrderDtoById(Long id) {
        log.info("Current method is - getOrderById");
        Order order = orderRepository.getOrderById(id).orElseThrow(OrderNotFoundException::new);

        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public Order getOrderById(Long id) {
        log.info("Current method is - getOrderById");
        return orderRepository.getOrderById(id).orElseThrow(OrderNotFoundException::new);
    }
    @Override
    public List<OrderDto> getAllOrders() {
        log.info("Current method is - getAllOrders");
        List<Order> orders = orderRepository.findAll();

        return orderMapper.ordersToOrderDto(orders);
    }

    @Override
    public CreatedOrderDto createOrder(CreatedCustomerDto customerDto, Long restaurantId, MenuItems menuItems) {
        CreatedOrderDto createdOrderDto = new CreatedOrderDto();
        createdOrderDto.setSecretPaymentUrl(createUrlForOrderPayment());
        createdOrderDto.setEstimatedTimeOfArrival("30");

        List<Restaurant> restaurants = kitchenFeign.getRestaurants();
        Restaurant restaurant = restaurants.stream()
                .filter(id -> id.getId() == restaurantId)
                .findFirst().orElseThrow();

        Customer customer = customerRepository.save(createCustomer(customerDto));
        List<OrderItem> orderItems = orderItemService.createOrderItems(restaurant, menuItems);

        Order order = new Order();
        order.setStatus(OrderStatus.ACTIVE);
        order.setRestaurant(restaurant);
        order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        order.setCustomer(customer);
        order.setItems(orderItems);
        orderRepository.save(order);

        createdOrderDto.setId(order.getId());
        return createdOrderDto;
    }

    private Customer createCustomer(CreatedCustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAddress(getAndSaveAddress(customerDto.getAddress()));
        return customer;
    }

    private Address getAndSaveAddress(AddressDto addressDto) {
        Address address = new Address();
        List<String> strings = Stream.of(addressDto.getAddress().split(","))
                .map(String::new)
                .collect(Collectors.toList());

        address.setCity(strings.get(0));
        address.setStreet(strings.get(1));
        address.setBuilding(strings.get(2));
        addressRepository.save(address);
        return address;
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

    private int generateRandomInt() {
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

    @Override
    public void updateOrderStatusById(Long id, OrderStatus status) {
        Order order = orderRepository.getOrderById(id).orElseThrow(OrderNotFoundException::new);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
