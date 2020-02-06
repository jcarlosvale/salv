package com.salv.orders.dto;

import com.salv.orders.entity.OrderDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDetailsDto {

    private Long id;
    private ProductDto product;
    private int quantity;
    private BigDecimal value;

    public OrderDetailsDto(OrderDetails orderDetails) {
        this.id = orderDetails.getId();
        this.product = new ProductDto(orderDetails.getProduct());
        this.quantity = orderDetails.getQuantity();
        this.value = orderDetails.getValue();
    }
}
