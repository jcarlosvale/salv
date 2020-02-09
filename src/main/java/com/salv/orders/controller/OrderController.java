package com.salv.orders.controller;

import com.salv.orders.dto.OrderDto;
import com.salv.orders.dto.request.OrderRequest;
import com.salv.orders.dto.response.OrdersResponse;
import com.salv.orders.exception.CustomException;
import com.salv.orders.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller Class exposing the two endpoints
 */
@RestController
@AllArgsConstructor
@Log
public class OrderController {

    private final OrderService service;

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

    @PostMapping(value = "/order/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder (@RequestBody OrderRequest orderRequest) {
        try {
            return new ResponseEntity<>(service.createOrder(orderRequest), HttpStatus.CREATED);
        } catch (IllegalArgumentException | CustomException ex) {
            log.info(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
