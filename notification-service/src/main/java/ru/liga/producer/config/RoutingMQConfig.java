package ru.liga.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingMQConfig {
    @Bean
    public Declarables myQueue() {
        Queue queueDirectFirst = new Queue("myQueue1", false);
        Queue queueDirectSecond = new Queue("myQueue2", false);
        DirectExchange directExchange = new DirectExchange("directExchange");

        return new Declarables(queueDirectFirst, queueDirectSecond, directExchange,
                BindingBuilder.bind(queueDirectFirst).to(directExchange).with("courier.free"),
                BindingBuilder.bind(queueDirectSecond).to(directExchange).with("courier.other"));
    }

    @Bean
    public Declarables myQueueFanout() {
        Queue queueTopicFirst = new Queue("myTopicQueue1", false);
        Queue queueTopicSecond = new Queue("myTopicQueue2", false);
        TopicExchange topicExchange = new TopicExchange("topicExchange");

        return new Declarables(queueTopicFirst, queueTopicSecond, topicExchange,
                BindingBuilder.bind(queueTopicFirst).to(topicExchange).with("*.other"),
                BindingBuilder.bind(queueTopicSecond).to(topicExchange).with("*.free"));
    }
}
