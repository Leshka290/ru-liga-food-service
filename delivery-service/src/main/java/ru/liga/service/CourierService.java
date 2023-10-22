package ru.liga.service;

import ru.liga.dto.CourierDto;
import ru.liga.dto.CourierStatus;

import java.util.List;

public interface CourierService {
    CourierDto addCourier(CourierDto courierDto);

    List<CourierDto> getAllByStatus(CourierStatus status);

    CourierDto updateStatus(Long id, CourierStatus status);
}
