package ru.liga.service;

import ru.liga.dto.DeliveryDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.dto.OrderAction;

import java.util.List;


public interface DeliveryService {
    List<DeliveryDto> getAllByStatus(DeliveryStatus status);

    void setOrderAction(Long id, OrderAction orderAction);
}
