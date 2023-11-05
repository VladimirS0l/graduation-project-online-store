package ru.solarev.core.coreservice.service;

import ru.solarev.api.apiservice.core.OrderDetailsDto;
import ru.solarev.api.apiservice.core.enums.OrderStatus;
import ru.solarev.core.coreservice.model.Order;


import java.util.List;

public interface OrderService {

    Order save(String username, OrderDetailsDto orderDetailsDto);

    void changeStatus(OrderStatus orderStatus, Long id);
    List<Order> findAll();
    List<Order> findAllByUsername(String username);

    Order getOrderById(Long id);

    void deleteOrder(Long id);
}
