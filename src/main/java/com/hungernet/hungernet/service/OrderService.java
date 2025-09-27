package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.OrderConverter;
import com.hungernet.hungernet.dto.OrderDto;
import com.hungernet.hungernet.dto.OrderDtoRequest;
import com.hungernet.hungernet.entity.*;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.OrderRepository;
import com.hungernet.hungernet.repository.RestaurantRepository;
import com.hungernet.hungernet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderConverter orderConverter, OrderRepository orderRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.orderConverter = orderConverter;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Could not find order with this id: " + orderId));
        return orderConverter.toDto(order);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderConverter::toDto).toList();
    }

    public OrderDto createOrder(OrderDtoRequest orderDtoRequest) {

        User user = userRepository.findById(orderDtoRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Could not find User with id: " + orderDtoRequest.getUserId()));

        Restaurant restaurant = restaurantRepository.findById(orderDtoRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Could not find Restaurant with id: " + orderDtoRequest.getRestaurantId()));

        List<OrderItem> orderItems = orderDtoRequest.getOrderItems().stream()
                .map(orderItemDtoRequest -> {
                    MenuItem menuItem = menuItemRepository.findBy(orderItemDtoRequest.getMenuItemId())
                })

        Order order = orderConverter.fromRequestDto(orderDtoRequest, restaurant, user,);
        Order savedOrder = orderRepository.save(order);
        return orderConverter.toDto(savedOrder);
    }

}
