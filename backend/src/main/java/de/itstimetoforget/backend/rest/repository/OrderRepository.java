package de.itstimetoforget.backend.rest.repository;

import de.itstimetoforget.backend.rest.entity.Order;
import de.itstimetoforget.backend.rest.entity.OrderState;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT co FROM Order co where co.state=:#{#state}")
    List<Order> getOrdersByState(@Param("state")OrderState state);
}
