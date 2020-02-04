package com.salv.orders.dto;

import com.salv.orders.entity.Client;
import lombok.Data;

@Data
public class ClientDto {

    private final Long id;
    private final String name;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
    }
}
