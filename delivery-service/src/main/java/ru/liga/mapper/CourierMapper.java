package ru.liga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.liga.dto.CourierDto;
import ru.liga.entity.Courier;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CourierMapper {

    List<CourierDto> couriersToCouriersDto(List<Courier> couriers);

    CourierDto courierToCourierDto(Courier courier);

    Courier courierDtoToCourier(CourierDto courierDto);
}
