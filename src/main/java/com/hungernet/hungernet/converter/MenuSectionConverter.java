package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.MenuSectionDto;
import com.hungernet.hungernet.dto.MenuSectionDtoCreate;
import com.hungernet.hungernet.dto.MenuSectionDtoUpdate;
import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.MenuSection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuSectionConverter {

    public MenuSectionDto toDto(MenuSection menuSection) {
        if (menuSection == null) return null;

        MenuSectionDto menuSectionDto = new MenuSectionDto();
        menuSectionDto.setMenuSectionId(menuSection.getMenuSectionId());
        menuSectionDto.setSection(menuSection.getSectionName());

        if (menuSection.getMenu() != null) {
            menuSectionDto.setMenuId(menuSectionDto.getMenuId());
        }

        if (menuSection.getMenuItems() != null) {
            menuSectionDto.setMenuItemIds(menuSection.getMenuItems().stream().map(MenuItem::getMenuItemId).toList());
        }

        return menuSectionDto;
    }

    public MenuSection fromCreateDto(MenuSectionDtoCreate menuSectionDtoCreate, Menu menu) {
        if (menuSectionDtoCreate == null) return null;

        MenuSection menuSection = new MenuSection();
        menuSection.setSectionName(menuSection.getSectionName());
        menuSection.setMenu(menu);
        return menuSection;
    }

    public void updateEntity(MenuSection menuSection, MenuSectionDtoUpdate menuSectionDtoUpdate, Menu menu, List<MenuItem> menuItems) {
        if (menuSectionDtoUpdate != null) {
            menuSection.setSectionName(menuSectionDtoUpdate.getSectionName());
        }

        if (menu != null) {
            menuSection.setMenu(menu);
        }

        if (menuItems != null) {
            menuSection.setMenuItems(menuItems);
        }
    }

}
