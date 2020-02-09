package com.salv.orders.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class OrderRequest {

    private String description;
    private Long clientId;
    private Set<ProductRequest> products;

}
