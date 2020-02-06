package com.salv.orders.dto;

import com.salv.orders.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String description;
    private LocalDate date;
    private BigDecimal totalValue;
    private ClientDto client;
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
