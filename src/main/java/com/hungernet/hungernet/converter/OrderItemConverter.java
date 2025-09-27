package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.OrderItemDto;
import com.hungernet.hungernet.dto.OrderItemDtoRequest;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.OrderItem;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.OrderItemRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    private final MenuItemRepository menuItemRepository;

    public OrderItemConverter(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public OrderItemDto toDto(OrderItem orderItem) {

        if (orderItem == null) return null;

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderItemId(orderItem.getOrderItemId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setItemPrice(orderItem.getPrice());

        if (orderItem.getOrder() != null) {
            orderItemDto.setOrderId(orderItem.getOrder().getOrderId());
        }
        if (orderItem.getMenuItem() != null) {
            orderItemDto.setMenuItemId(orderItem.getMenuItem().getMenuItemId());
            orderItemDto.setMenuItemName(orderItem.getMenuItem().getItemName());
        }

        return orderItemDto;
    }

    public OrderItem fromRequestDto(OrderItemDtoRequest orderItemDtoRequest) {
        if (orderItemDtoRequest == null) return null;

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDtoRequest.getQuantity());

        MenuItem menuItem = menuItemRepository.findById(orderItemDtoRequest.getMenuItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find corresponding menu item of this id: " + orderItemDtoRequest.getMenuItemId()));
        orderItem.setMenuItem(menuItem);
        orderItem.setPrice(menuItem.getItemPrice());

        return orderItem;
    }

}
