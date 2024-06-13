package de.itstimetoforget.backend.rest.processor;

import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProcessor {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        order.getPasswords().forEach(password -> password.setOrder(order));
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
