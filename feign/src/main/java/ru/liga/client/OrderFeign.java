package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.liga.dto.OrderStatus;
import ru.liga.entity.Order;

import java.util.Optional;

@FeignClient(name = "order-service", url = "http://localhost:8080")
public interface OrderFeign {

    @GetMapping("/order/{id}")
    Optional<Order> getOrderById(@PathVariable Long id);

    @PostMapping("/order/update/{id}")
    void updateOrderStatusById(@PathVariable Long id, @RequestParam OrderStatus status);
}
