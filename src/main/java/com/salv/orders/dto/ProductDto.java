package com.salv.orders.dto;

import com.salv.orders.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String description;
    private BigDecimal price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
