package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.dto.*;
import com.hungernet.hungernet.service.MenuSectionService;
import com.hungernet.hungernet.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menus")
public class MenuController {

    private final MenuService menuService;
    private final MenuSectionService menuSectionService;

    public MenuController(MenuService menuService, MenuSectionService menuSectionService) {
        this.menuService = menuService;
        this.menuSectionService = menuSectionService;
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping("/{menuId}")
    public MenuDto getMenuById(@PathVariable("menuId") Long menuId) {
        return menuService.getMenuById(menuId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<MenuDto> getAllMenus() {
        return menuService.getAllMenus();
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @PostMapping
    public MenuDto createMenu(@RequestBody @Valid MenuDtoCreate menuDtoCreate) {
        return menuService.createMenu(menuDtoCreate);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @PutMapping("/{menuId}")
    public MenuDto updateMenu(@PathVariable("menuId") Long menuId, @RequestBody @Valid MenuDtoUpdate menuDtoUpdate) {
        return menuService.updateMenu(menuId, menuDtoUpdate);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @DeleteMapping("/{menuId}")
    public void deleteMenuById(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenuById(menuId);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping("/restaurant/{restaurantId}/active")
    public MenuDto getActiveMenusByRestaurant(@PathVariable Long restaurantId) {
        return menuService.getActiveMenuByRestaurant(restaurantId);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping("{menuId}/sections")
    public List<MenuSectionDto> getSectionsByMenu(@PathVariable Long menuId) {
        return menuService.getSectionsByMenu(menuId);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @PostMapping("{menuId}/sections")
    public MenuSectionDto addSectionToMenu(@PathVariable Long menuId, @RequestBody MenuSectionDtoCreate menuSectionDtoCreate) {
        return menuService.addSectionToMenu(menuId,menuSectionDtoCreate);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @PutMapping("sections/{sectionId}")
    public MenuSectionDto updateSection(@PathVariable Long menuSectionId, @RequestBody MenuSectionDtoUpdate menuSectionDtoUpdate) {
        return menuService.updateMenuSection(menuSectionId, menuSectionDtoUpdate);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @DeleteMapping("sections/{sectionId}")
    public void deleteSection(@PathVariable Long sectionId){
        menuService.deleteSection(sectionId);
    }

    @GetMapping("sections/{sectionId}/menuItems")
    public List<MenuItemDto> getMenuItemsBySection(@PathVariable Long sectionId) {
        return menuService.getItemsBysection(sectionId);
    }

    //check if items are pulled from the section they belong
    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @PostMapping("/sections/{sectionId}/items")
    public MenuItemDto addMenuItemToSection(@PathVariable Long sectionId, @RequestBody MenuItemDtoCreate menuItemDtoCreate) {
        return menuService.addItemsToSection(sectionId, menuItemDtoCreate);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @PutMapping("/sections/{sectionId}/items/{itemId}")
    public MenuItemDto updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItemDtoUpdate menuItemDtoUpdate){
        return menuService.updateMenuItem(menuItemId, menuItemDtoUpdate);
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @DeleteMapping("/sections/{sectionId}/items/{itemId}")
    public void deleteMenuItem(@PathVariable Long menuItemId){
        menuService.deleteMenuItem(menuItemId);
    }

}
