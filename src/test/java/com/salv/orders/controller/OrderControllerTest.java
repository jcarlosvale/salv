package com.salv.orders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salv.orders.dto.OrderDto;
import com.salv.orders.dto.request.OrderRequest;
import com.salv.orders.dto.response.OrdersResponse;
import com.salv.orders.entity.Order;
import com.salv.orders.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrderControllerTest {

    @LocalServerPort
    private int randomServerPort = 0;

    private final String getOrders = "/orders";
    private final String insertOrder = "/order/new";

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testGetOrders() {
        String url = "http://localhost:"+randomServerPort+getOrders;
        ResponseEntity<OrdersResponse> actualResponse =
                new RestTemplate().getForEntity(url, OrdersResponse.class);
        assertEquals(5, Objects.requireNonNull(actualResponse.getBody()).getOrders().size());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void testInsertOrder() throws IOException {
        String url = "http://localhost:"+randomServerPort+insertOrder;
        OrderRequest orderRequest = readFromFile("requestOK.json", OrderRequest.class);
        ResponseEntity<OrderDto> actualResponse = new RestTemplate().postForEntity(url, orderRequest,OrderDto.class);
        OrderDto actualOrderDto = actualResponse.getBody();
        assert actualOrderDto != null;
        Optional<Order> order = orderRepository.findById(actualOrderDto.getId());
        assert order.isPresent();
        OrderDto expectedOrderDto = new OrderDto(order.get());
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testBadRequest() {
        String url = "http://localhost:"+randomServerPort+insertOrder;
        new RestTemplate().postForEntity(url, new OrderRequest(), OrderDto.class);
    }

    private <T> T readFromFile(String filename, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:json/" + filename);
        assert file.exists();
        return objectMapper.readValue(file, clazz);
    }
}