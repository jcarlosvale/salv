package com.salv.orders.dto.response;

import com.salv.orders.dto.OrderDto;
import lombok.Data;

import java.util.List;

@Data
public class OrdersResponse {

    private List<OrderDto> orders;

}
