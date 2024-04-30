package de.itstimetoforget.backend.rest.repository;

import de.itstimetoforget.backend.rest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
