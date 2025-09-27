package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.MenuDto;
import com.hungernet.hungernet.dto.MenuDtoCreate;
import com.hungernet.hungernet.dto.MenuDtoUpdate;
import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.entity.MenuSection;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.enums.MenuType;
import org.springframework.stereotype.Component;

@Component
public class MenuConverter {

    public MenuDto toDto(Menu menu) {

        if (menu == null)
            return null;

        MenuDto menuDto = new MenuDto();
        menuDto.setMenuId(menu.getMenuId());
        menuDto.setMenuType(menu.getMenuType());
        menuDto.setStartTime(menu.getStartTime());
        menuDto.setEndTime(menu.getEndTime());
        menuDto.setActive(menu.isActive());

        if (menu.getRestaurant() != null) {
            menuDto.setRestaurantId(menu.getRestaurant().getRestaurantId());
        }

        if (menu.getMenuSections() != null) {
            menuDto.setMenuSectionIds(menu.getMenuSections().stream().map(MenuSection::getMenuSectionId).toList());
        }

        return menuDto;

    }

    public Menu fromCreateDto(MenuDtoCreate menuDtoCreate, Restaurant restaurant) {

        if (menuDtoCreate == null) return null;

        Menu menu = new Menu();
        menu.setMenuType(menu.getMenuType());
        menu.setStartTime(menuDtoCreate.getStartTime());
        menu.setEndTime(menuDtoCreate.getEndTime());
        menu.setRestaurant(restaurant);

        return menu;

    }

    public void updateEntity(Menu menu, MenuDtoUpdate menuDtoUpdate) {

        if (menuDtoUpdate.getMenuType() != null) {
            menu.setMenuType(MenuType.valueOf(menuDtoUpdate.getMenuType()));
        }

        if  (menuDtoUpdate.getStartTime() != null) {
            menu.setStartTime(menuDtoUpdate.getStartTime());
        }

        if (menuDtoUpdate.getEndTime() != null) {
            menu.setEndTime(menuDtoUpdate.getEndTime());
        }

    }

}
