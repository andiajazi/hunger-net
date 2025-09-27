package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.MenuItemDto;
import com.hungernet.hungernet.dto.MenuItemDtoCreate;
import com.hungernet.hungernet.dto.MenuItemDtoUpdate;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.MenuSection;
import com.hungernet.hungernet.entity.OrderItem;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuSectionRepository;
import org.springframework.stereotype.Component;

import java.nio.file.ReadOnlyFileSystemException;

@Component
public class MenuItemConverter {

    private final MenuSectionRepository menuSectionRepository;

    public MenuItemConverter(MenuSectionRepository menuSectionRepository) {
        this.menuSectionRepository = menuSectionRepository;
    }

    public MenuItemDto toDto(MenuItem menuItem) {

        if (menuItem == null) return null;

        MenuItemDto menuItemDto = new MenuItemDto();
        menuItemDto.setMenuItemId(menuItem.getMenuItemId());
        menuItemDto.setItemName(menuItem.getItemName());
        menuItemDto.setItemDescription(menuItem.getItemDescription());
        menuItemDto.setItemPrice(menuItem.getItemPrice());

        if (menuItem.getMenuSection() != null) {
            menuItemDto.setSectionId(menuItem.getMenuSection().getMenuSectionId());
        }

        if (menuItem.getOrderItems() != null) {
            menuItemDto.setOrderItemIds(menuItem.getOrderItems().stream().map(OrderItem::getOrderItemId).toList());
        }

        return menuItemDto;

    }

    public MenuItem fromCreateDto(MenuItemDtoCreate menuItemDtoCreate) {
        if (menuItemDtoCreate == null) return null;

        MenuItem menuItem = new MenuItem();
        menuItem.setItemName(menuItemDtoCreate.getItemName());
        menuItem.setItemDescription(menuItemDtoCreate.getItemDescription());
        menuItem.setItemPrice(menuItemDtoCreate.getItemPrice());

        if (menuItemDtoCreate.getSectionId() != null) {
            MenuSection menuSection = menuSectionRepository.findById(menuItemDtoCreate.getSectionId())
                            .orElseThrow(() -> new ResourceNotFoundException("Menu section with id: " + menuItemDtoCreate.getSectionId() + " not found"));
            menuItem.setMenuSection(menuSection);
        }
        return menuItem;
    }

    public void updateEntity(MenuItemDtoUpdate menuItemDtoUpdate, MenuItem menuItem) {
        if (menuItemDtoUpdate.getItemName() != null) {
            menuItem.setItemName(menuItemDtoUpdate.getItemName());
        }
        if (menuItemDtoUpdate.getItemDescription() != null) {
            menuItem.setItemDescription(menuItemDtoUpdate.getItemDescription());
        }
        if (menuItemDtoUpdate.getItemPrice() != null) {
            menuItem.setItemPrice(menuItemDtoUpdate.getItemPrice());
        }
        if (menuItemDtoUpdate.getSectionId() != null) {
            MenuSection menuSection = menuSectionRepository.findById(menuItemDtoUpdate.getSectionId())
                            .orElseThrow(() -> new ResourceNotFoundException("There is no section with this id: " + menuItemDtoUpdate.getSectionId()));
            menuItem.setMenuSection(menuSection);
        }
    }

}
