package com.salv.orders.dto;

import com.salv.orders.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private final Long id;
    private final String description;
    private final BigDecimal price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
