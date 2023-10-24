package ru.liga.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.dto.CourierStatus;
import ru.liga.dto.OrderAction;
import ru.liga.entity.Courier;
import ru.liga.exception.CourierFreeNotFoundException;
import ru.liga.repository.CourierRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueueListener {
    private final CourierRepository courierRepository;

    @RabbitListener(queues = "findFreeCourier")
    public void findFreeCourier(String message) {
        log.info("Received from findFreeCourier : " + orderAction(message).name());

        List<Courier> couriers = courierRepository.getCouriersByStatus(CourierStatus.FREE);
        log.info("Free couriers: " + couriers);

        Courier courier = couriers.stream().findAny().orElseThrow(CourierFreeNotFoundException::new);
        courier.setStatus(CourierStatus.ACTIVE);
        log.info("Courier " + courier + " is active");
    }

    private OrderAction orderAction(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderAction orderAction;

        try {
            orderAction = objectMapper.readValue(message, OrderAction.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orderAction;
    }
}
