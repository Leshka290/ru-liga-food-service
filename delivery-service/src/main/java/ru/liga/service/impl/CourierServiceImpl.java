package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.client.KitchenFeign;
import ru.liga.dto.CourierDto;
import ru.liga.dto.CourierStatus;
import ru.liga.dto.RestaurantDto;
import ru.liga.entity.Courier;
import ru.liga.entity.Restaurant;
import ru.liga.mapper.CourierMapper;
import ru.liga.repository.CourierRepository;
import ru.liga.service.CourierService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;
    private final KitchenFeign kitchenFeign;

    @Override
    public CourierDto addCourier(CourierDto courierDto) {
        courierRepository.save(courierMapper.courierDtoToCourier(courierDto));

        return courierDto;
    }

    @Override
    public List<CourierDto> getAllByStatus(CourierStatus status) {
        List<Courier> couriers = courierRepository.getCouriersByStatus(status);

        return courierMapper.couriersToCouriersDto(couriers);
    }

    @Override
    public CourierDto updateStatus(Long id, CourierStatus status) {
        Courier courier = courierRepository.findById(id).orElseThrow();
        courier.setStatus(status);
        courierRepository.save(courier);

        return courierMapper.courierToCourierDto(courier);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return kitchenFeign.getRestaurants();
    }
}
