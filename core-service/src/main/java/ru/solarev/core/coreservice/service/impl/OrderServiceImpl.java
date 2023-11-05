package ru.solarev.core.coreservice.service.impl;

import ru.solarev.core.coreservice.dto.OrderDetailsDto;
import ru.solarev.core.coreservice.model.Order;
import ru.solarev.core.coreservice.model.OrderStatus;
import ru.solarev.core.coreservice.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order save(String username, OrderDetailsDto orderDetailsDto) {
        return null;
    }

    @Override
    public void changeStatus(OrderStatus orderStatus, Long id) {

    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
