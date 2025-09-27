package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.converter.MenuConverter;
import com.hungernet.hungernet.dto.MenuDto;
import com.hungernet.hungernet.dto.MenuDtoCreate;
import com.hungernet.hungernet.dto.MenuDtoUpdate;
import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menus/")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
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

}
