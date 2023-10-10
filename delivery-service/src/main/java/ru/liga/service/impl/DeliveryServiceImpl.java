package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.entity.Delivery;
import ru.liga.mapper.DeliveryMapper;
import ru.liga.repository.DeliveryRepository;
import ru.liga.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public OrderActionDto setOrderAction(Long id) {
        log.info("Current method is - setOrderAction");
        Delivery delivery = deliveryRepository.getDeliveryById(id);
        return deliveryMapper.deliveryToOrderActionDto(delivery);
    }

    @Override
    public List<DeliveryDto> getAllByStatus(DeliveryStatus status) {
        log.info("Current method is - getAllByStatus");
        List<Delivery> deliveries = deliveryRepository.getAllByStatus(status);
//                return deliveryMapper.deliveryToDeliveryDto(deliveries);
        //Заглушка для проверки контроллера
        List<DeliveryDto> list = new ArrayList<>();
        DeliveryDto dto = new DeliveryDto();
        dto.setCustomer("c");
        dto.setRestaurant("restaurant");
        dto.setOrder_id(2);
        dto.setPayment("payment");
        list.add(dto);
        return list;
    }
}
