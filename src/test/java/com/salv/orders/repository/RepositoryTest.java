package com.salv.orders.repository;

import com.salv.orders.entity.Client;
import com.salv.orders.entity.Order;
import com.salv.orders.entity.OrderDetails;
import com.salv.orders.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    public void validateClientRepositoryTest() {
        Client client = new Client(1L, "some name");
        clientRepository.save(client);
        client = clientRepository.getOne(1L);
        assertEquals(1, clientRepository.findAll().size());
        assertNotNull(client);
    }

    @Test
    @Transactional
    public void validateProductRepositoryTest() {
        Product product = new Product(1L, "some product", BigDecimal.ONE);
        productRepository.save(product);
        product = productRepository.getOne(1L);
        assertEquals(1, productRepository.findAll().size());
        assertNotNull(product);
    }

    @Test
    @Transactional
    public void validateOrderTest() {
        Client client = new Client(1L, "some name");
        clientRepository.save(client);

        Product product1 = new Product(1L, "some product 1", BigDecimal.ONE);
        productRepository.save(product1);

        Product product2 = new Product(2L, "some product 2", BigDecimal.ONE);
        productRepository.save(product2);

        OrderDetails orderDetails1 = new OrderDetails(product1, 10, BigDecimal.ONE);
        OrderDetails orderDetails2 = new OrderDetails(product2, 15, BigDecimal.ONE);
        Order order = new Order(client, "some order", BigDecimal.ONE, orderDetails1, orderDetails2);

        orderRepository.save(order);

        List<Order> listOfOrder = orderRepository.findAll();
        assertEquals(1, listOfOrder.size());

        Order actualOrder = listOfOrder.get(0);
        assertEquals(order,actualOrder);
        assertEquals(2, actualOrder.getOrderDetails().size());
    }
}