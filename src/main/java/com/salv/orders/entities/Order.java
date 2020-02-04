package com.salv.orders.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/*
Order is a reserved keyword for H2DB, then the table name is different of the entity.
 */
@Entity(name = "REQUEST")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;
}
