package ru.solarev.core.coreservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.solarev.api.apiservice.core.OrderDetailsDto;
import ru.solarev.api.apiservice.core.OrderDto;
import ru.solarev.api.apiservice.core.enums.OrderStatus;
import ru.solarev.api.apiservice.exceptions.ValidationException;
import ru.solarev.core.coreservice.converter.OrderMapper;
import ru.solarev.core.coreservice.service.OrderService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrderController {
    private final OrderService ordersService;
    private final OrderMapper orderMapper;

    @Operation(summary = "Создать заказ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto save(@RequestHeader String username,
                         @RequestBody @Valid
                         OrderDetailsDto orderDetailsDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new ValidationException("Ошибка валидации", errors);
        }
        return orderMapper.toDto(ordersService.save(username, orderDetailsDto));
    }

    @Operation(summary = "Все заказы")
    @GetMapping
    public List<OrderDto> getAll() {
        return orderMapper.toDto(ordersService.findAll());
    }

    @Operation(summary = "Все заказы текущего пользователя")
    @GetMapping("/username")
    public List<OrderDto> getAllByUsername(@RequestHeader String username) {
        return orderMapper.toDto(ordersService.findAllByUsername(username));
    }

    @Operation(summary = "Показать заказ по ID")
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderMapper.toDto(ordersService.getOrderById(id));
    }

    @Operation(summary = "Запрос на получение статусов заказа")
    @GetMapping("/statuses")
    public OrderStatus[] getAllStatuses() {
        return OrderStatus.values();
    }

    @Operation(summary = "Изменить статус заказа")
    @PutMapping("/statuses/{id}")
    public void changeStatus(@RequestParam OrderStatus orderStatus,
                             @PathVariable Long id) {
        ordersService.changeStatus(orderStatus, id);
    }

    @Operation(summary = "Удалить заказ по ID")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ordersService.deleteOrder(id);
    }
}
