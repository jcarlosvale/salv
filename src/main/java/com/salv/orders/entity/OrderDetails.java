package com.salv.orders.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/*
Order is a reserved keyword for H2DB, then the table name is different of the entity.
 */
@Entity(name = "REQUEST_DETAILS")
@NoArgsConstructor
@Getter
@ToString(exclude = "order")
@Data
public class OrderDetails {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Order order;

    @ManyToOne
    @JoinColumn
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal value;

    public OrderDetails(@NonNull Long id, @NonNull Product product, int quantity, @NonNull BigDecimal value) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.value = value;
    }
}
