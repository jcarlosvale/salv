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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public OrdersResponse getOrders() {
        return convertToOrdersResponse(orderRepository.findAll());
    }

    @Transactional
    public OrderDto createOrder(OrderRequest orderRequest) throws CustomException {
        if (null == orderRequest) throw new CustomException("Order request is null.");
        Client client = retrieveClientFromId(orderRequest.getClientId());

        Set<OrderDetails> orderDetails = new HashSet<>();
        double total = 0.0;
        for (ProductRequest productRequest : orderRequest.getProducts()) {
            OrderDetails details = createOrderDetails(productRequest);
            total += details.getValue().doubleValue();
            orderDetails.add(details);
        }

        BigDecimal totalValue = BigDecimal.valueOf(total).setScale(2, RoundingMode.DOWN);

        Order order = new Order(client, orderRequest.getDescription(), totalValue, orderDetails.toArray(new OrderDetails[0]));

        order = orderRepository.save(order);

        return new OrderDto(order);
    }

    private OrderDetails createOrderDetails(ProductRequest productRequest) throws CustomException {
        Product product = retrieveProduct(productRequest.getId());
        if (productRequest.getQuantity() <= 0) throw new CustomException("Quantity value not allowed [<=0 ]");
        int quantity = productRequest.getQuantity();
        BigDecimal value =
                BigDecimal.valueOf(quantity * product.getPrice().doubleValue()).setScale(2, RoundingMode.DOWN);
        return new OrderDetails(product, quantity, value);
    }

    private Product retrieveProduct(Long productId) {
        return productRepository.getOne(productId);
    }

    private Client retrieveClientFromId(Long clientId) {
        return clientRepository.getOne(clientId);
    }

    private OrdersResponse convertToOrdersResponse(List<Order> orders) {
        OrdersResponse ordersResponse = new OrdersResponse();
        if (null != orders) {
            ordersResponse.setOrders(orders.stream().map(OrderDto::new).collect(Collectors.toList()));
        }
        return ordersResponse;
    }
}
