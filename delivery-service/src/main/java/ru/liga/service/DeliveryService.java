package ru.liga.service;

import ru.liga.dto.DeliveryDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.dto.OrderActionDto;

import java.util.List;


public interface DeliveryService {

    List<DeliveryDto> getAllByStatus(DeliveryStatus status);
    OrderActionDto setOrderAction(Long id);
}
