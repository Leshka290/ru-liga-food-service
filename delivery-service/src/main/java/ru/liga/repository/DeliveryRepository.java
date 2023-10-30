package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.dto.DeliveryStatus;
import ru.liga.entity.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> getAllByStatus(DeliveryStatus status);

    Delivery getDeliveryById(Long id);

    Optional<Delivery> getDeliveryByOrder_Id(Long id);
}
