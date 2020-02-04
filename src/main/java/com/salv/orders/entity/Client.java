package com.salv.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//TODO surrogate keys, other fields like address, contact, ....
public class Client {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
}
