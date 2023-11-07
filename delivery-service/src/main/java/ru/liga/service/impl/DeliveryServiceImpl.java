package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.client.OrderFeign;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.dto.OrderAction;
import ru.liga.dto.OrderStatus;
import ru.liga.entity.Delivery;
import ru.liga.entity.Order;
import ru.liga.exception.OrderNotFoundException;
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
    private final OrderFeign orderFeign;

    @Override
    public void setOrderAction(Long id, OrderAction orderAction) {
        log.info("Current method is - setOrderAction");
        Order order = orderFeign.getOrderById(id).orElseThrow(OrderNotFoundException::new);
        Delivery delivery = new Delivery();
        delivery.setRestaurant(order.getRestaurant());
        delivery.setOrder(order);
        delivery.setCustomer(order.getCustomer());
        delivery.setPayment(order.getRestaurant().getPayment());
        delivery.setCustomer(order.getCustomer());

        switch (orderAction) {
            case ACCEPT:
                order.setStatus(OrderStatus.ACTIVE);
                orderFeign.updateOrderStatusById(id, OrderStatus.ACTIVE);
                delivery.setStatus(DeliveryStatus.ACTIVE);
                deliveryRepository.save(delivery);
                break;
            case COMPLETE:
                order.setStatus(OrderStatus.COMPLETE);
                orderFeign.updateOrderStatusById(id, OrderStatus.COMPLETE);
                delivery.setStatus(DeliveryStatus.COMPLETE);
                deliveryRepository.save(delivery);
                break;
        }
    }

    @Override
    public List<DeliveryDto> getAllByStatus(DeliveryStatus status) {
        log.info("Current method is - getAllByStatus");
        List<Delivery> deliveries = deliveryRepository.getAllByStatus(status);
        return deliveryMapper.deliveryToDeliveryDto(deliveries);
    }
}
