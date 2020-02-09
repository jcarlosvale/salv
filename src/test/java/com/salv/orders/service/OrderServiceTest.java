package com.salv.orders.service;

import com.salv.orders.dto.OrderDto;
import com.salv.orders.dto.request.OrderRequest;
import com.salv.orders.dto.request.ProductRequest;
import com.salv.orders.dto.response.OrdersResponse;
import com.salv.orders.entity.Client;
import com.salv.orders.entity.Order;
import com.salv.orders.entity.OrderDetails;
import com.salv.orders.entity.Product;
import com.salv.orders.exception.CustomException;
import com.salv.orders.repository.ClientRepository;
import com.salv.orders.repository.OrderRepository;
import com.salv.orders.repository.ProductRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private final Random random = new Random(System.currentTimeMillis());

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    OrderService orderService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getOrdersTest() {
        Order order = createSomeOrder();
        List<Order> orders = new ArrayList<>();
        List<OrderDto> ordersDto = new ArrayList<>();
        orders.add(order);
        ordersDto.add(new OrderDto(order));
        when(orderRepository.findAll()).thenReturn(orders);
        OrdersResponse actualOrders = orderService.getOrders();
        OrdersResponse expectedOrders = new OrdersResponse();
        expectedOrders.setOrders(ordersDto);
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    public void createOrderTest() throws CustomException {
        Order expectedOrder = new Order();
   //     actualOrder.setId(random.nextLong());
        expectedOrder.setDescription("some order description");
        expectedOrder.setDate(LocalDate.now());
        expectedOrder.setClient(new Client(1L, "some client name"));
        expectedOrder.setTotalValue(BigDecimal.valueOf(5).setScale(2, RoundingMode.DOWN));

        Product product1 = new Product(1L, "product1", BigDecimal.valueOf(3));
        Product product2 = new Product(2L, "product2", BigDecimal.valueOf(2));

        OrderDetails orderDetails1 = new OrderDetails(product1, 1, product1.getPrice());
        OrderDetails orderDetails2 = new OrderDetails(product2, 1, product2.getPrice());

        Set<OrderDetails> setOrderDetails = new HashSet<>();
        setOrderDetails.add(orderDetails1);
        setOrderDetails.add(orderDetails2);

        expectedOrder.setOrderDetails(setOrderDetails);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setDescription(expectedOrder.getDescription());
        orderRequest.setClientId(expectedOrder.getClient().getId());
        Set<ProductRequest> setProductRequest = new HashSet<>();
        setProductRequest.add(new ProductRequest(product1.getId(), 1));
        setProductRequest.add(new ProductRequest(product2.getId(), 1));
        orderRequest.setProducts(setProductRequest);

        when(clientRepository.getOne(orderRequest.getClientId())).thenReturn(expectedOrder.getClient());
        when(productRepository.getOne(product1.getId())).thenReturn(product1);
        when(productRepository.getOne(product2.getId())).thenReturn(product2);
        when(orderRepository.save(expectedOrder)).thenReturn(expectedOrder);

        OrderDto actualOrderDto = orderService.createOrder(orderRequest);

        OrderDto expectedOrderDto = new OrderDto(expectedOrder);
        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test(expected = CustomException.class)
    public void createOrderEmptyOrderRequestTest() throws CustomException {
        OrderRequest orderRequest = new OrderRequest();
        orderService.createOrder(orderRequest);
    }

    @Test(expected = CustomException.class)
    public void createOrderNullOrderRequestTest() throws CustomException {
        orderService.createOrder(null);
    }

    private Order createSomeOrder() {
        long someValue = random.nextInt();
        Client client = createSomeClient();
        OrderDetails orderDetails = createSomeOrderDetails();
        return new Order(client,"some order " + someValue, BigDecimal.valueOf(someValue), orderDetails);
    }

    private OrderDetails createSomeOrderDetails() {
        Product product = createSomeProduct(BigDecimal.valueOf(1));
        int quantity = random.nextInt(11);
        return new OrderDetails(product, quantity, BigDecimal.valueOf(quantity));
    }

    private Product createSomeProduct(BigDecimal value) {
        long id = random.nextInt();
        return new Product(id, "some product " + id, value);
    }

    private Client createSomeClient() {
        long id = random.nextInt();
        return new Client(id, "some client " + id);
    }
}