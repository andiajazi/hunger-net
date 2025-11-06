package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.dto.OrderDto;
import com.hungernet.hungernet.dto.OrderDtoRequest;
import com.hungernet.hungernet.dto.OrderDtoUpdate;
import com.hungernet.hungernet.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@RequestBody @Valid Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid OrderDtoRequest orderDtoRequest) {
        return orderService.createOrder(orderDtoRequest);
    }

    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    @PutMapping("/id/{oderId}")
    public OrderDto updateOrder(@PathVariable("orderId") Long orderId, @RequestBody @Valid OrderDtoUpdate orderDtoUpdate) {
        return orderService.updateOrder(orderId, orderDtoUpdate);
    }

}
