package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrderDto;
import ru.liga.dto.OrderStatus;
import ru.liga.service.OrderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final OrderService orderService;

    @GetMapping("/orders/{status}")
    public ResponseEntity<List<OrderDto>> getAllByStatus(@PathVariable OrderStatus status) {
        log.info("Request GET orders by status");
        return ResponseEntity.ok(orderService.getAllByStatus(status));
    }
}
