package ru.solarev.core.coreservice.service;

import ru.solarev.core.coreservice.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    public List<OrderItem> getAllOrderItems();

    public OrderItem getOrderItemById(Long id);

    public OrderItem createOrderItem(OrderItem orderItem);

    public void updateOrderItem(OrderItem orderItem);

    public void deleteOrderItem(Long id);
}
