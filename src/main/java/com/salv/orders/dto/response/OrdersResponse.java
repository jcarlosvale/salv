package com.salv.orders.dto.response;

import com.salv.orders.dto.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrdersResponse {

    private List<OrderDto> orders;

}
