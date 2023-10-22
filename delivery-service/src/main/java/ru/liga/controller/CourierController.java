package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.dto.*;
import ru.liga.service.CourierService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/courier")
@RestController
public class CourierController {
    private final CourierService courierService;
    @Operation(summary = "Добавление курьера")
    @ApiResponse(responseCode = "200", description = "add")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping()
    public ResponseEntity<CourierDto> addCourier(CourierDto courierDto) {
        log.info("Request POST courier");

        return ResponseEntity.ok(courierService.addCourier(courierDto));
    }

    @Operation(summary = "Обновление статуса курьера")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/update_status/{id}")
    public ResponseEntity<?> updateCourier(@PathVariable Long id, CourierStatus status) {
        log.info("Request PATCH update courier status");
        return ResponseEntity.ok(courierService.updateStatus(id, status));
    }

    @Operation(summary = "Получение всех курьеров по статусу")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @GetMapping("/all/{status}")
    public ResponseEntity<List<CourierDto>> getAllByStatus(@PathVariable CourierStatus status) {
        log.info("Request GET orders by status");
        return ResponseEntity.ok(courierService.getAllByStatus(status));
    }
}
