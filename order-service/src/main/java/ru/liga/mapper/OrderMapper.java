package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.MenuItems;
import ru.liga.dto.OrderDto;
import ru.liga.entity.Item;
import ru.liga.entity.Order;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "order.id", target = "id")
    @Mapping(source = "order.timestamp", target = "timestamp")
    @Mapping(source = "order.restaurant", target = "restaurant")
    @Mapping(source = "order.items", target = "items")
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "order.id", target = "id")
    @Mapping(source = "order.timestamp", target = "timestamp")
    @Mapping(source = "order.restaurant", target = "restaurant")
    @Mapping(source = "order.items", target = "items")
    List<OrderDto> ordersToOrderDto(List<Order> orders);

    @Mapping(source = "item.id", target = "menu_item_id")
    MenuItems itemsToMenuItems(int quantity, Item item);
}
