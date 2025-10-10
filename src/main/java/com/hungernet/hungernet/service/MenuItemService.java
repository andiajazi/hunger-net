package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.MenuItemConverter;
import com.hungernet.hungernet.dto.MenuItemDto;
import com.hungernet.hungernet.dto.MenuItemDtoCreate;
import com.hungernet.hungernet.dto.MenuItemDtoUpdate;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private  final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;
    private final MenuItemConverter menuItemConverter;

    public MenuItemService(MenuItemRepository menuItemRepository, OrderRepository orderRepository, MenuItemConverter menuItemConverter) {
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
        this.menuItemConverter = menuItemConverter;
    }

    public List<MenuItemDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream().map(menuItemConverter::toDto).toList();
    }

    public MenuItemDto getMenuItemById(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find menu item with id: " + menuItemId));
        return menuItemConverter.toDto(menuItem);
    }

    @Transactional
    public MenuItemDto createMenuItem(MenuItemDtoCreate menuItemDtoCreate) {
        MenuItem menuItem = menuItemConverter.fromCreateDto(menuItemDtoCreate);

        return menuItemConverter.toDto(menuItem);
    }

    @Transactional
    public MenuItemDto updateMenuItem(Long menuItemId, MenuItemDtoUpdate menuItemDtoUpdate) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find the menu item with id: " + menuItemId));
        menuItemConverter.updateEntity(menuItemDtoUpdate, menuItem);

        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return menuItemConverter.toDto(updatedMenuItem);
    }

    @Transactional
    public void deleteMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find menu item with id: " + menuItemId));

        menuItemRepository.delete(menuItem);
    }

}
