package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;
import ru.liga.entity.Delivery;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(source = "delivery.order", target = "order_id")
    @Mapping(source = "delivery.restaurant", target = "restaurant")
    @Mapping(source = "delivery.customer", target = "customer")
    @Mapping(source = "delivery.payment", target = "payment")
    List<DeliveryDto> deliveryToDeliveryDto(List<Delivery> delivery);

    @Mapping(source = "delivery.status", target = "order_action")
    OrderActionDto deliveryToOrderActionDto(Delivery delivery);
}
