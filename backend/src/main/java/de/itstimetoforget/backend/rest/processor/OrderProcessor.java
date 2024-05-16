package de.itstimetoforget.backend.rest.processor;

import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
