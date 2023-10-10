package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;
import ru.liga.entity.Delivery;
import ru.liga.entity.Item;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeliveryMapper {
    List<DeliveryDto> deliveryToDeliveryDto(List<Delivery> delivery);

    @Mapping(source = "delivery.status", target = "order_action")
    OrderActionDto deliveryToOrderActionDto(Delivery delivery);

    @Mapping(source = "item.id", target = "menu_item_id")
    MenuItems itemsToMenuItems(int quantity, Item item);
}
