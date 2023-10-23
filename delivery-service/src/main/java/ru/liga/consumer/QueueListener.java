package ru.liga.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderAction;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueueListener {

    @RabbitListener(queues = "myQueue1")
    public void processMyQueue(String message) {
        log.info("Received from myQueue1 : " + orderAction(message).name());
        log.info("Свободные курьреры");
    }

    @RabbitListener(queues = "myQueue2")
    public void processMyQueue2(String message) {
        log.info("Received from myQueue2 : " + orderAction(message).name());
        log.info("Курьеры с заказами");
    }

    @RabbitListener(queues = "myTopicQueue1")
    public void processMyTopicQueue(String message) {
        log.info("Received from myTopicQueue1 : " + orderAction(message).name());
        log.info("Курьеры с заказами");
    }

    @RabbitListener(queues = "myTopicQueue2")
    public void processMyTopicQueue2(String message) {
        log.info("Received from myTopicQueue2 : " + orderAction(message).name());
        log.info("Свободные курьреры");
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
