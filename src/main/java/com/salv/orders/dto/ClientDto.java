package com.salv.orders.dto;

import com.salv.orders.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private String name;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
    }
}
