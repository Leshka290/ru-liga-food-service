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

    List<DeliveryDto> deliveryToDeliveryDto(List<Delivery> delivery);

    @Mapping(source = "delivery.status", target = "orderAction")
    OrderActionDto deliveryToOrderActionDto(Delivery delivery);
}
