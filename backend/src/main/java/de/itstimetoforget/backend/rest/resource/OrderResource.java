package de.itstimetoforget.backend.rest.resource;

import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
