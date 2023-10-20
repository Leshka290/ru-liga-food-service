package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.dto.OrderStatus;
import ru.liga.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getOrderById(Long id);
    List<Order> getOrdersByStatus(OrderStatus orderStatus);
}
