package ru.liga.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.dto.*;
import ru.liga.service.OrderService;
import ru.liga.service.RestaurantService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final OrderService orderService;
    private final RestaurantService restaurantService;

    @GetMapping("/orders/{status}")
    public ResponseEntity<List<OrderDto>> getAllByStatus(@PathVariable OrderStatus status) {
        log.info("Request GET orders by status");
        return ResponseEntity.ok(orderService.getAllByStatus(status));
    }

    @GetMapping("/menu_items")
    public ResponseEntity<List<RestaurantMenuItemDto>> getMenuItemsByRestaurant(@RequestBody RestaurantDto restaurantDto) {
        log.info("Request GET menu items by restaurant");
        List<RestaurantMenuItemDto> restaurantMenuItemsDto = restaurantService
                .getRestaurantMenuItemsByRestaurant(restaurantDto);

        if (restaurantMenuItemsDto != null) {
            return ResponseEntity.ok(restaurantMenuItemsDto);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RestaurantMenuItemDto.class))
    )
    @PostMapping(value = "/menu_items", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantMenuItemDto> createMenuItem(@RequestBody RestaurantMenuItemDto restaurantMenuItemDto,
                                                                @RequestBody @Valid MultipartFile image) {
        log.info("Request GET menu items by restaurant");

        return ResponseEntity.ok(restaurantService.createRestaurantMenuItem(restaurantMenuItemDto, image));
    }

    @PostMapping("/menu_item/update_price")
    public ResponseEntity<?> updatePriceMenuItem(@RequestBody UpdatePriceMenuItemDto updatePriceMenuItemDto) {
        log.info("Request GET menu items by restaurant");
        restaurantService.updatePriceMenuItem(updatePriceMenuItemDto);
        return ResponseEntity.ok().build();
    }
}
