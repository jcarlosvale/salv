package com.salv.orders.controller;

import com.salv.orders.dto.response.OrdersResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrderControllerTest {

    @LocalServerPort
    private int randomServerPort = 0;

    private final String getOrders = "/orders";
    private final String insertOrder = "/order/new";

    @Test
    public void testGetOrders() {
        String url = "http://localhost:"+randomServerPort+getOrders;

        ResponseEntity<OrdersResponse> actualResponse =
                new RestTemplate().getForEntity(url, OrdersResponse.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

}