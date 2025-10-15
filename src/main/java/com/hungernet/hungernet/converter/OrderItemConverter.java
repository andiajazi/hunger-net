package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.OrderItemDto;
import com.hungernet.hungernet.dto.OrderItemDtoRequest;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.Order;
import com.hungernet.hungernet.entity.OrderItem;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
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
        orderItemDto.setItemPrice(orderItem.getMenuItem().getItemPrice());

        if (orderItem.getOrder() != null) {
            orderItemDto.setOrderId(orderItem.getOrder().getOrderId());
        }
        if (orderItem.getMenuItem() != null) {
            orderItemDto.setMenuItemId(orderItem.getMenuItem().getMenuItemId());
            orderItemDto.setMenuItemName(orderItem.getMenuItem().getItemName());
        }

        return orderItemDto;
    }

    public OrderItem fromRequestDto(OrderItemDtoRequest orderItemDtoRequest, Order order) {
        if (orderItemDtoRequest == null) return null;

        OrderItem orderItem = new OrderItem();
        MenuItem menuItem = menuItemRepository.findById(orderItemDtoRequest.getMenuItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item with id: " + orderItemDtoRequest.getMenuItemId() + " not found!"));

        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(orderItemDtoRequest.getQuantity());
        orderItem.setPrice(menuItem.getItemPrice());
        orderItem.setOrder(order);

        return orderItem;
    }

    public void updateEntity(OrderItem entity, OrderItemDtoRequest orderItemDtoRequest) {
        if (orderItemDtoRequest.getQuantity() != null) {
            entity.setQuantity(orderItemDtoRequest.getQuantity());
        }
        if (orderItemDtoRequest.getMenuItemId() != null) {
            MenuItem menuItem = menuItemRepository.findById(orderItemDtoRequest.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "MenuItem not found with id: " + orderItemDtoRequest.getMenuItemId()
                    ));
            entity.setMenuItem(menuItem);
            entity.setPrice(menuItem.getItemPrice());
        }
    }

}
