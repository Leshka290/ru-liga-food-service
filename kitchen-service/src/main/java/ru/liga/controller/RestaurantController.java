package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Restaurant;
import ru.liga.service.RestaurantService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Получение всех ресторанов")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    schema = @Schema(implementation = Restaurant.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        log.info("Request GET restaurants");

        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @Operation(summary = "Получение ресторана по id")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    schema = @Schema(implementation = RestaurantDto.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantDto> getRestaurants(@PathVariable Long id) {
        log.info("Request GET restaurant by id");

        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }
}

