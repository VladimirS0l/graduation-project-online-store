package ru.solarev.core.coreservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solarev.api.apiservice.card.CartDto;
import ru.solarev.api.apiservice.core.OrderDetailsDto;
import ru.solarev.api.apiservice.core.enums.OrderStatus;
import ru.solarev.api.apiservice.exceptions.InvalidParamsException;
import ru.solarev.api.apiservice.exceptions.ResourceNotFoundException;
import ru.solarev.core.coreservice.model.Order;
import ru.solarev.core.coreservice.model.OrderItem;
import ru.solarev.core.coreservice.repository.OrderRepository;
import ru.solarev.core.coreservice.service.OrderService;
import ru.solarev.core.coreservice.service.ProductService;
import ru.solarev.core.coreservice.service.web.CartWebClient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartWebClient cartWebClient;

    @Override
    public Order save(String username, OrderDetailsDto orderDetailsDto) {
        if (username == null || orderDetailsDto == null) {
            throw new InvalidParamsException("Невалидные параметры");
        }
        CartDto currentCart = cartWebClient.getUserCart(username);
        Order order = new Order();
        order.setFullName(orderDetailsDto.getFullName());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        order.setOrderStatus(OrderStatus.CREATED);
        Set<OrderItem> items = currentCart.getItems().stream()
                .map(i -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(i.getQuantity());
                    item.setPricePerProduct(i.getPricePerProduct());
                    item.setPrice(i.getPrice());
                    item.setProduct(productService.getProductById(i.getProductId()));
                    return item;
                }).collect(Collectors.toSet());
        order.setItems(items);
        orderRepository.save(order);
        cartWebClient.clearUserCart(username);
        return order;
    }

    @Override
    public void changeStatus(OrderStatus orderStatus, Long id) {
        if (orderStatus == null || id == null) {
            throw new InvalidParamsException("Невалидные параметры");
        }
        try {
            orderRepository.changeStatus(orderStatus, id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Ошибка изменения статуса заказа. Заказ " + id + "не существует");
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Заказ не найден"));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
