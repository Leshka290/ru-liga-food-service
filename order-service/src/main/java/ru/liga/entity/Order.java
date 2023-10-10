package ru.liga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private long id;
    private Timestamp timestamp;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @OneToMany(mappedBy = "order")
    private List<Item> items;
}
