package ru.liga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "distance", nullable = false)
    private int distance;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
