package de.itstimetoforget.backend.rest.service;

import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.entity.OrderState;
import de.itstimetoforget.backend.rest.processor.OrderProcessor;
import de.itstimetoforget.backend.rest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderProcessor orderProcessor;

    @Autowired
    public OrderService(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    public Order createOrder(Order order) {
        return orderProcessor.saveOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderProcessor.getAllOrders();
    }

    public List<Order> getOrdersByState(OrderState state) {
        return orderProcessor.getOrdersByState(state);
    }
}
