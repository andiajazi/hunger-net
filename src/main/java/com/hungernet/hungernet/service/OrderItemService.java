package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.OrderItemConverter;
import com.hungernet.hungernet.dto.OrderItemDto;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.OrderItemRepository;
import com.hungernet.hungernet.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemConverter orderItemConverter;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderItemService(OrderItemConverter orderItemConverter, OrderItemRepository orderItemRepository, OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderItemConverter = orderItemConverter;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll().stream().map(orderItemConverter::toDto).toList();
    }

}
