package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.*;
import com.hungernet.hungernet.entity.Order;
import com.hungernet.hungernet.entity.OrderItem;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.entity.User;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.OrderItemRepository;
import com.hungernet.hungernet.repository.OrderRepository;
import com.hungernet.hungernet.repository.RestaurantRepository;
import com.hungernet.hungernet.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private final OrderRepository orderRepository;
    private final OrderItemConverter orderItemConverter;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderConverter(OrderRepository orderRepository, OrderItemConverter orderItemConverter, UserRepository userRepository, RestaurantRepository restaurantRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemConverter = orderItemConverter;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDto toDto(Order order) {
        if (order == null) return null;

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId(order.getOrderId());
        orderDto.setClientAddress(order.getClientAddress());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setOrderStatus(order.getOrderStatus());

        if (order.getUser() != null) {
            orderDto.setUserId(order.getUser().getUserId());
        }

        if (order.getRestaurant() != null) {
            orderDto.setRestaurantId(order.getRestaurant().getRestaurantId());
            orderDto.setRestaurantName(order.getRestaurant().getRestaurantName());
        }

        if (order.getOrderItems() != null) {
            List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                    .map(orderItemConverter::toDto).toList();
            orderDto.setOrderItemDtos(orderItemDtos);
        }

        return orderDto;
    }

    public Order fromRequestDto(OrderDtoRequest orderDtoRequest) {
        if (orderDtoRequest == null) return null;

        Order order = new Order();
        order.setClientAddress(orderDtoRequest.getClientAddress());
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderStatus(null);


        User user = userRepository.findById(orderDtoRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find related User with id: " + orderDtoRequest.getUserId()));
        order.setUser(user);


        Restaurant restaurant = restaurantRepository.findById(orderDtoRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find related restaurant with id: " + orderDtoRequest.getRestaurantId()));
        order.setRestaurant(restaurant);

        List<OrderItem> orderItems = orderDtoRequest.getOrderItems().stream().map(orderItemDto -> orderItemConverter.fromRequestDto(orderItemDto, order))
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        return order;
    }

    public void updateEntity(Order order, OrderDtoUpdate orderDtoUpdate) {
        if (orderDtoUpdate.getClientAddress() != null) {
            order.setClientAddress(orderDtoUpdate.getClientAddress());
        }

        if (orderDtoUpdate.getOrderStatus() != null) {
            order.setOrderStatus(orderDtoUpdate.getOrderStatus());
        }

        if (orderDtoUpdate.getUserId() != null) {
            User user = userRepository.findById(orderDtoUpdate.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Could not find related User with id: " + orderDtoUpdate.getUserId()));
            order.setUser(user);
        }

        if (orderDtoUpdate.getRestaurantId() != null) {
            Restaurant restaurant = restaurantRepository.findById(orderDtoUpdate.getRestaurantId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Could not find related Restaurant with id: " + orderDtoUpdate.getRestaurantId()));
            order.setRestaurant(restaurant);
        }

        if (orderDtoUpdate.getOrderItems() != null) {
            List<OrderItem> updatedOrderItems = orderDtoUpdate.getOrderItems().stream()
                    .map(orderItemDto -> orderItemConverter.fromRequestDto(orderItemDto, order)).toList();
            order.getOrderItems().clear();
            order.getOrderItems().addAll(updatedOrderItems);
        }
    }

}