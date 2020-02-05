package com.salv.orders.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class OrderRequest {

    private final String description;
    private final Long clientId;
    private final Set<ProductRequest> products;

}
