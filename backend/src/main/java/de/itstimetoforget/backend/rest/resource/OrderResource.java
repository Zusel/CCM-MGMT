package de.itstimetoforget.backend.rest.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.entity.OrderState;
import de.itstimetoforget.backend.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 4800)
@RequestMapping(path = "/order")
public class OrderResource {

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping(path = "")
    public List<Order> getAllOrders(@RequestParam Optional<String> filter, @RequestParam Optional<String> state) {
        List<Order> orders;
        if (state.isPresent()) {
            OrderState orderState = OrderState.getState(state.get());
            orders = orderService.getOrdersByState(orderState);
        } else {
            orders = orderService.getAllOrders();
        }

        if (filter.isPresent() && !filter.get().isEmpty()) {
            orders = orders.stream().filter(order -> {
                try {
                    return new ObjectMapper().writeValueAsString(order).contains(filter.get());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        }
        return orders;
    }

    @GetMapping(path = "/orderStates")
    public List<String> getAllOrderStates() {
        return Arrays.stream(OrderState.values()).map(OrderState::getName).toList();
    }
}
