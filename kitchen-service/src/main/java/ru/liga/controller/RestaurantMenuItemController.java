package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import ru.liga.dto.CreateOrUpdateItemDto;
import ru.liga.dto.RestaurantDto;
import ru.liga.dto.RestaurantMenuItemDto;
import ru.liga.dto.UpdatePriceMenuItemDto;
import ru.liga.service.RestaurantMenuItemService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/menu_item")
@RestController
public class RestaurantMenuItemController {
    private final RestaurantMenuItemService restaurantMenuItemService;

    @Operation(summary = "Получение всех товаров в меню ресторана")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RestaurantMenuItemDto.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantMenuItemDto>> getMenuItemsByRestaurant(RestaurantDto restaurantDto) {
        log.info("Request GET menu items by restaurant");

        return ResponseEntity.ok(restaurantMenuItemService
                .getRestaurantMenuItemsByRestaurant(restaurantDto));
    }

    @Operation(summary = "Получение всех товаров в меню ресторана")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RestaurantMenuItemDto.class)))
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<RestaurantMenuItemDto>> getMenuItemsByRestaurantId(@PathVariable Long id) {
        log.info("Request GET menu items by restaurant");

        return ResponseEntity.ok(restaurantMenuItemService
                .getRestaurantMenuItemsByRestaurantId(id));
    }

    @Operation(summary = "Добавление товара")
    @ApiResponse(responseCode = "200", description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RestaurantMenuItemDto.class))
    )
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantMenuItemDto> createMenuItem(String restaurantName, CreateOrUpdateItemDto properties,
                                                                @RequestBody MultipartFile image) {
        log.info("Request POST menu item");
        restaurantMenuItemService.createRestaurantMenuItem(restaurantName, properties, image);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление цены товара")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/update_price")
    public ResponseEntity<?> updatePriceMenuItem(@RequestBody UpdatePriceMenuItemDto updatePriceMenuItemDto) {
        log.info("Request PATCH update price item");
        restaurantMenuItemService.updatePriceMenuItem(updatePriceMenuItemDto);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление картинки товара",
            responses = {@ApiResponse(responseCode = "200", description = "OK"
                    , content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                    , schema = @Schema(type = "array", format = "byte"))),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found")}, tags = "Image")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateItemImage(@PathVariable Long id,
                                             @RequestBody MultipartFile imageFile) {
        log.info("Update image item id: {}", id);

        if (restaurantMenuItemService.updateImage(id, imageFile)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
