package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.dto.OrderAction;
import ru.liga.service.DeliveryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Operation(summary = "Изменение статуса заказа")
    @PostMapping("/{id}")
    public ResponseEntity<?> setOrderAction(@PathVariable Long id, OrderAction orderAction) {
        log.info("Request POST delivery order action");
        deliveryService.setOrderAction(id, orderAction);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение списка доставок по статусу")
    @GetMapping("/{status}")
    public ResponseEntity<List<DeliveryDto>> getAllByStatus(@PathVariable DeliveryStatus status) {
        log.info("Request GET delivery");
        return ResponseEntity.ok(deliveryService.getAllByStatus(status));
    }
}
