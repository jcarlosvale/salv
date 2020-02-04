package com.salv.orders.service;

import com.salv.orders.dto.OrderDto;
import com.salv.orders.dto.OrdersResponse;
import com.salv.orders.entity.Order;
import com.salv.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrdersResponse getOrders() {
        return convertToOrdersResponse(orderRepository.findAll());
    }

    private OrdersResponse convertToOrdersResponse(List<Order> orders) {
        OrdersResponse ordersResponse = new OrdersResponse();
        if (null != orders) {
            ordersResponse.setOrders(orders.stream().map(OrderDto::new).collect(Collectors.toList()));
        }
        return ordersResponse;
    }

}
