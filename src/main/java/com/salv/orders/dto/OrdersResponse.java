package com.salv.orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersResponse {

    private List<OrderDto> orders;

}
