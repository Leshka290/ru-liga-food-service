package ru.liga.producer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderAction;
import ru.liga.producer.service.CourierService;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourierServiceImpl implements CourierService {

    private final RabbitMQProducerServiceImpl rabbitMQProducerService;
    private final ObjectMapper objectMapper;

    public void sendOrderStatus(OrderAction orderAction) {
        String orderStatusInfo = tryToSerialyzeMessageAsString(orderAction);
        if (orderAction.name().equals("ACCEPT")) {
            rabbitMQProducerService.sendMessage(orderStatusInfo, "courier.free");
        } else {
            rabbitMQProducerService.sendMessage(orderStatusInfo, "courier.other");
        }
    }

    private String tryToSerialyzeMessageAsString(OrderAction orderAction) {
        String orderStatusInfo = null;
        try {
            orderStatusInfo = objectMapper.writeValueAsString(orderAction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orderStatusInfo;
    }
}
