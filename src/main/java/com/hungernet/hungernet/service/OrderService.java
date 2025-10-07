package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.OrderConverter;
import com.hungernet.hungernet.converter.OrderItemConverter;
import com.hungernet.hungernet.dto.OrderDto;
import com.hungernet.hungernet.dto.OrderDtoRequest;
import com.hungernet.hungernet.dto.OrderDtoUpdate;
import com.hungernet.hungernet.entity.*;
import com.hungernet.hungernet.enums.OrderStatus;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.OrderRepository;
import com.hungernet.hungernet.repository.RestaurantRepository;
import com.hungernet.hungernet.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemConverter orderItemConverter;

    public OrderService(OrderConverter orderConverter, OrderRepository orderRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, OrderItemConverter orderItemConverter) {
        this.orderConverter = orderConverter;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemConverter = orderItemConverter;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderConverter::toDto).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Could not find order with this id: " + orderId));
        return orderConverter.toDto(order);
    }

    @Transactional
    public OrderDto createOrder(OrderDtoRequest orderDtoRequest) {
        Order order = orderConverter.fromRequestDto(orderDtoRequest);

        if (order.getOrderStatus() == null) {
            order.setOrderStatus(OrderStatus.PENDING);
        }

        Order savedOrder = orderRepository.save(order);

        return orderConverter.toDto(savedOrder);
    }

    @Transactional
    public OrderDto updateOrder(Long orderId, OrderDtoUpdate orderDtoUpdate) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find order with id: " + orderId));

        orderConverter.updateEntity(order, orderDtoUpdate);

        Order updatedOrder = orderRepository.save(order);
        return orderConverter.toDto(updatedOrder);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("The order with this id: " + orderId + " does not exist."));
        orderRepository.delete(order);
    }

}
