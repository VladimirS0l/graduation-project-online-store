package ru.solarev.core.coreservice.service;

import ru.solarev.core.coreservice.dto.OrderDetailsDto;
import ru.solarev.core.coreservice.model.Order;
import ru.solarev.core.coreservice.model.OrderStatus;

import java.util.List;

public interface OrderService {

    Order save(String username, OrderDetailsDto orderDetailsDto);

    void changeStatus(OrderStatus orderStatus, Long id);
    public List<Order> getAllOrders();

    public Order getOrderById(Long id);

    public Order createOrder(Order order);

    public void deleteOrder(Long id);
}
