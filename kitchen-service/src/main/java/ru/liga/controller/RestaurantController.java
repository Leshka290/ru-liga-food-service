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
import ru.liga.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/menu_item")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Получение всех товаров в меню ресторана")
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantMenuItemDto>> getMenuItemsByRestaurant(@RequestBody RestaurantDto restaurantDto) {
        log.info("Request GET menu items by restaurant");
        List<RestaurantMenuItemDto> restaurantMenuItemsDto = restaurantService
                .getRestaurantMenuItemsByRestaurant(restaurantDto);

        if (restaurantMenuItemsDto != null) {
            return ResponseEntity.ok(restaurantMenuItemsDto);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Operation(summary = "Добавление товара")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RestaurantMenuItemDto.class))
    )
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantMenuItemDto> createMenuItem(@RequestPart("properties") @Valid CreateOrUpdateItemDto properties,
                                                                @RequestBody @Valid MultipartFile image) {
        log.info("Request POST menu item");
        System.out.println(properties.getDescription());
        return ResponseEntity.ok(restaurantService.createRestaurantMenuItem(properties, image));
    }

    @Operation(summary = "Обновление цены товара")
    @PostMapping("/update_price")
    public ResponseEntity<?> updatePriceMenuItem(@RequestBody UpdatePriceMenuItemDto updatePriceMenuItemDto) {
        log.info("Request GET menu items by restaurant");
        restaurantService.updatePriceMenuItem(updatePriceMenuItemDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление картинки объявления",
            responses = {@ApiResponse(responseCode = "200", description = "OK"
                    , content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                    , schema = @Schema(type = "array", format = "byte"))),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found")}, tags = "Image")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAdsImage(@PathVariable Long id,
                                            @RequestParam MultipartFile imageFile) {
        log.info("Update image ags id: {}", id);
        if (restaurantService.updateImage(id, imageFile)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

