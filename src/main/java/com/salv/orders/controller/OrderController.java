package com.salv.orders.controller;

import com.salv.orders.dto.OrdersResponse;
import com.salv.orders.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
public class OrderController {

    private final OrderService service;

    @GetMapping(value = "/order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello () {
        return "Hello World";
    }

    @GetMapping(value = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersResponse> getOrders () {
        try {
            return new ResponseEntity<>(service.getOrders(), HttpStatus.OK);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
