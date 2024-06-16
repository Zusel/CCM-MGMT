package de.itstimetoforget.backend.rest.processor;

import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.entity.OrderState;
import de.itstimetoforget.backend.rest.repository.OrderRepository;
import de.itstimetoforget.backend.rest.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProcessor {
    private final OrderRepository orderRepository;
    private final PasswordRepository passwordRepository;

    @Autowired
    public OrderProcessor(OrderRepository orderRepository, PasswordRepository passwordRepository) {
        this.orderRepository = orderRepository;
        this.passwordRepository = passwordRepository;
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        if (!order.getPasswords().isEmpty()) {
            order.getPasswords().forEach(password -> password.setOrder(order));
            passwordRepository.saveAll(order.getPasswords());
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByState(OrderState state) {
        return orderRepository.getOrdersByState(state);
    }
}
