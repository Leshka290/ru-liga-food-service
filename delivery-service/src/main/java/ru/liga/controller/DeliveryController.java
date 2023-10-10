package ru.liga.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.DeliveryDto;
import ru.liga.dto.OrderActionDto;
import ru.liga.dto.DeliveryStatus;
import ru.liga.service.DeliveryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/delivery")
@RestController
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/{id}")
    public ResponseEntity<OrderActionDto> setOrderAction(@PathVariable Long id) {
        log.info("Request POST delivery order action");
        return ResponseEntity.ok(deliveryService.setOrderAction(id));
    }
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/{status}")
    public ResponseEntity<List<DeliveryDto>> getAllByStatus(@PathVariable DeliveryStatus status) {
        log.info("Request GET delivery");
        return ResponseEntity.ok(deliveryService.getAllByStatus(status));
    }
}
