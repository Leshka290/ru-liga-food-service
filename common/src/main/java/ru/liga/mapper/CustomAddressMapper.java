package ru.liga.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.entity.Address;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomAddressMapper {

    public Address addressToAddressDto(String addressStr) {
        Address address = new Address();
        List<String> strings = Stream.of(addressStr.split(","))
                .map(String::new)
                .collect(Collectors.toList());

        address.setCity(strings.get(0));
        address.setStreet(strings.get(1));
        address.setBuilding(strings.get(2));
        return address;
    }
}
