package com.salv.orders.dto;

import com.salv.orders.entity.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderDto {

    private final Long id;
    private final String description;
    private final LocalDate date;
    private final BigDecimal totalValue;
    private final ClientDto client;
    private Set<OrderDetailsDto> orderDetails;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.date = order.getDate();
        this.totalValue = order.getTotalValue();
        this.client = new ClientDto(order.getClient());
        this.orderDetails = order.getOrderDetails().stream().map(OrderDetailsDto::new).collect(Collectors.toSet());
    }
}
