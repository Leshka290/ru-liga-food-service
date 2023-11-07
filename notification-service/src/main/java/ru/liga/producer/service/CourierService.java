package ru.liga.producer.service;

import ru.liga.dto.OrderAction;

public interface CourierService {
    void sendOrderStatus(OrderAction orderAction);
}
