package ru.liga.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CreatedOrderDto;
import ru.liga.dto.MenuItems;
import ru.liga.dto.OrderDto;
import ru.liga.dto.OrderStatus;
import ru.liga.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        log.info("Request GET order by id");
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("Request GET all orders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping()
    public ResponseEntity<CreatedOrderDto> createOrder(@Valid Long restaurantId,
                                                    @RequestBody MenuItems menuItems) {
        log.info("Request POST addOrder");
        return ResponseEntity.ok(orderService.createOrder(restaurantId, menuItems));
    }

    @GetMapping("/orders/{status}")
    public ResponseEntity<List<OrderDto>> getAllByStatus(@PathVariable OrderStatus status) {
        log.info("Request GET orders by status");
        return ResponseEntity.ok(orderService.getAllByStatus(status));
    }
}
