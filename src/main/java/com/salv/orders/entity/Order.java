package com.salv.orders.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Order is a reserved keyword for H2DB, then the table name is different of the entity.
 */
@Entity(name = "REQUEST")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "orderDetails")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetails;

    public Order(Client client, String description, BigDecimal totalValue, OrderDetails ... orderDetails) {
        this.client = client;
        this.description = description;
        this.totalValue = totalValue;
        Stream.of(orderDetails).forEach(od1 -> od1.setOrder(this));
        this.orderDetails = Stream.of(orderDetails).collect(Collectors.toSet());
        this.date = LocalDate.now();
    }
}
