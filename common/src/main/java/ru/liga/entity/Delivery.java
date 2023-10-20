package ru.liga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.dto.DeliveryStatus;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", nullable = false)
    private long id;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
