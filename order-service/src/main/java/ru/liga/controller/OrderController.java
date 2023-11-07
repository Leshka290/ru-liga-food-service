package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.*;
import ru.liga.entity.Order;
import ru.liga.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Получение заказа по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/dto/{id}")
    public ResponseEntity<OrderDto> getOrderDtoById(@PathVariable Long id) {
        log.info("Request GET orderDto by id");
        return ResponseEntity.ok(orderService.getOrderDtoById(id));
    }

    @Operation(summary = "Получение полной информации о заказе по id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        log.info("Request GET order by id");
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Получение всех заказов")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("Request GET all orders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Создание заказа")
    @ApiResponse(responseCode = "200", description = "Created",
            content = @Content(
                    schema = @Schema(implementation = CreatedOrderDto.class))
    )
    @PostMapping()
    public ResponseEntity<CreatedOrderDto> createOrder(@RequestBody CreatedCustomerDto customerDto, MenuItems menuItems,
                                                       @Valid Long restaurantId) {
        log.info("Request POST createOrder");
        return ResponseEntity.ok(orderService.createOrder(customerDto, restaurantId, menuItems));
    }

    @Operation(summary = "Получение всех заказов по статусу")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @GetMapping("/orders/{status}")
    public ResponseEntity<List<OrderDto>> getAllByStatus(@PathVariable OrderStatus status) {
        log.info("Request GET orders by status");
        return ResponseEntity.ok(orderService.getAllByStatus(status));
    }

    @Operation(summary = "Обновление статуса заказа")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/update/{id}")
    private void updateOrderStatusById(@PathVariable Long id, @RequestParam OrderStatus status) {
        log.info("Request PATCH order status by id");
        orderService.updateOrderStatusById(id, status);
        ResponseEntity.ok().build();
    }
}
