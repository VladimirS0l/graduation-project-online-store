package ru.solarev.core.coreservice.service;

import ru.solarev.core.coreservice.model.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();

    public Order getOrderById(Long id);

    public Order createOrder(Order order);

    public void updateOrder(Order order);

    public void deleteOrder(Long id);
}
