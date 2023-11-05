package ru.solarev.core.coreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.solarev.api.apiservice.core.enums.OrderStatus;
import ru.solarev.core.coreservice.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.username = ?1")
    List<Order> findAllByUsername(String username);

    @Modifying
    @Query("update Order o set o.orderStatus = ?1, o.updatedAt = CURRENT_TIMESTAMP where o.id = ?2")
    void changeStatus(OrderStatus orderStatus, Long orderId);
}
