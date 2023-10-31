package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
