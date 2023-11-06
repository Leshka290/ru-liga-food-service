package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.client.KitchenFeign;
import ru.liga.dto.MenuItems;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.exception.RestaurantMenuItemNotFoundException;
import ru.liga.repository.OrderItemRepository;
import ru.liga.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final KitchenFeign kitchenFeign;

    @Override
    public List<OrderItem> createOrderItems(Restaurant restaurant, MenuItems menuItems) {

        List<RestaurantMenuItemDto> restaurantMenuItems = kitchenFeign.getMenuItemsByRestaurantId(restaurant.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        RestaurantMenuItemDto restaurantMenuItemDto = restaurantMenuItems
                .stream()
                .filter(index -> index.getId() == menuItems.getIdMenuItem())
                .findFirst().orElseThrow(RestaurantMenuItemNotFoundException::new);

        OrderItem orderItem = new OrderItem();
        orderItem.setName(restaurantMenuItemDto.getName());
        orderItem.setPrice(restaurantMenuItemDto.getPrice());
        orderItem.setDescription(restaurantMenuItemDto.getDescription());
        orderItem.setQuantity(menuItems.getQuantity());
        orderItems.add(orderItem);
        orderItemRepository.save(orderItem);
        return orderItems;
    }
}
