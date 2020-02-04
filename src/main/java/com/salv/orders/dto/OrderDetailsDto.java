package com.salv.orders.dto;

import com.salv.orders.entity.OrderDetails;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailsDto {

    private final Long id;
    private final ProductDto product;
    private final int quantity;
    private final BigDecimal value;

    public OrderDetailsDto(OrderDetails orderDetails) {
        this.id = orderDetails.getId();
        this.product = new ProductDto(orderDetails.getProduct());
        this.quantity = orderDetails.getQuantity();
        this.value = orderDetails.getValue();
    }
}
