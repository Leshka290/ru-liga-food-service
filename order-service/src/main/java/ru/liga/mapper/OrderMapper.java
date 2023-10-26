package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.MenuItems;
import ru.liga.dto.OrderDto;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Order;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "order", target = "order.status")
    List<OrderDto> ordersToOrderDto(List<Order> orders);

    @Mapping(source = "item.id", target = "idMenuItem")
    MenuItems itemsToMenuItems(int quantity, OrderItem item);
}
