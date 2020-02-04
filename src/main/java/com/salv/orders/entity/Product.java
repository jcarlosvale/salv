package com.salv.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
//TODO: create tax rate, quantity in the stock
public class Product {
    @Id
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    public Product(Long id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }
}
