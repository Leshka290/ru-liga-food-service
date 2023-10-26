package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.dto.OrderAction;
import ru.liga.entity.Delivery;
import ru.liga.entity.Order;
import ru.liga.mapper.DeliveryMapper;
import ru.liga.repository.DeliveryRepository;
import ru.liga.service.DeliveryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public void setOrderAction(Long id, OrderAction orderAction) {
        log.info("Current method is - setOrderAction");
        Order order = new Order();
        //Послать в ресторан информацию о новом заказе
        Delivery delivery = new Delivery();
        delivery.setId(id);
        delivery.setStatus(DeliveryStatus.ACTIVE);
        deliveryRepository.save(delivery);
    }

    @Override
    public List<DeliveryDto> getAllByStatus(DeliveryStatus status) {
        log.info("Current method is - getAllByStatus");
        List<Delivery> deliveries = deliveryRepository.getAllByStatus(status);
        return deliveryMapper.deliveryToDeliveryDto(deliveries);
    }
}
