package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.dto.*;
import com.hungernet.hungernet.service.MenuSectionService;
import com.hungernet.hungernet.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menus/")
public class MenuController {

    private final MenuService menuService;
    private final MenuSectionService menuSectionService;

    public MenuController(MenuService menuService, MenuSectionService menuSectionService) {
        this.menuService = menuService;
        this.menuSectionService = menuSectionService;
    }

    @GetMapping("id/{menuId}")
    public MenuDto getMenuById(@PathVariable("menuId") Long menuId) {
        return menuService.getMenuById(menuId);
    }

    @GetMapping
    public List<MenuDto> getAllMenus() {
        return menuService.getAllMenus();
    }

    @PostMapping
    public MenuDto createMenu(@RequestBody @Valid MenuDtoCreate menuDtoCreate) {
        return menuService.createMenu(menuDtoCreate);
    }

    @PutMapping("id/{menuId}")
    public MenuDto updateMenu(@PathVariable("menuId") Long menuId, @RequestBody @Valid MenuDtoUpdate menuDtoUpdate) {
        return menuService.updateMenu(menuId, menuDtoUpdate);
    }

    @DeleteMapping("id/{menuId}")
    public void deleteMenuById(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenuById(menuId);
    }

    @GetMapping("/restaurant/{restaurantId}/active")
    public MenuDto getActiveMenusByRestaurant(@PathVariable Long restaurantId) {
        return menuService.getActiveMenuByRestaurant(restaurantId);
    }

    @GetMapping("{menuId}/sections")
    public List<MenuSectionDto> getSectionsByMenu(@PathVariable Long menuId) {
        return menuService.getSectionsByMenu(menuId);
    }

    @PostMapping("{menuId}/sections")
    public MenuSectionDto addSectionToMenu(@PathVariable Long menuId, @RequestBody MenuSectionDtoCreate menuSectionDtoCreate) {
        return menuService.addSectionToMenu(menuId,menuSectionDtoCreate);
    }

    @PutMapping("sections/{sectionId}")
    public MenuSectionDto updateSection(@PathVariable Long menuSectionId, @RequestBody MenuSectionDtoUpdate menuSectionDtoUpdate) {
        return menuService.updateMenuSection(menuSectionId, menuSectionDtoUpdate);
    }

    @DeleteMapping("sections/{sectionId}")
    public void deleteSection(@PathVariable Long sectionId){
        menuService.deleteSection(sectionId);
    }

    @GetMapping("sections/{sectionId}/menuItems")
    public List<MenuItemDto> getMenuItemsBySection(@PathVariable Long sectionId) {
        return menuService.getItemsBysection(sectionId);
    }

    @PostMapping("/sections/{sectionId}/items")
    public MenuItemDto addMenuItemToSection(@PathVariable Long sectionId, @RequestBody MenuItemDtoCreate menuItemDtoCreate) {
        return menuService.addItemsToSection(sectionId, menuItemDtoCreate);
    }

    @PutMapping("/items/{itemId}")
    public MenuItemDto updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItemDtoUpdate menuItemDtoUpdate){
        return menuService.updateMenuItem(menuItemId, menuItemDtoUpdate);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteMenuItem(@PathVariable Long menuItemId){
        menuService.deleteMenuItem(menuItemId);
    }

}
