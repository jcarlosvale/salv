package com.salv.orders.dto.request;

import lombok.Data;

@Data
public class ProductRequest {

    private final Long id;
    private final Integer quantity;

}
