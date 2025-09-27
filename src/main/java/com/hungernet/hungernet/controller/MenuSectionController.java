package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.dto.MenuSectionDto;
import com.hungernet.hungernet.dto.MenuSectionDtoCreate;
import com.hungernet.hungernet.dto.MenuSectionDtoUpdate;
import com.hungernet.hungernet.service.MenuSectionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menuSections/")
public class MenuSectionController {

    private final MenuSectionService menuSectionService;

    public MenuSectionController(MenuSectionService menuSectionService) {
        this.menuSectionService = menuSectionService;
    }

    @GetMapping
    public List<MenuSectionDto> getAllMenuSections(){
        return menuSectionService.getAllMenuSections();
    }

    @GetMapping("id/{menuSectionId}")
    public MenuSectionDto getMenuSectionById(Long menuSectionId) {
        return menuSectionService.getMenuSectionById(menuSectionId);
    }

    @PostMapping
    public MenuSectionDto createMenuSection(@RequestBody @Valid MenuSectionDtoCreate menuSectionDtoCreate) {
        return menuSectionService.createMenuSection(menuSectionDtoCreate);
    }

    @PutMapping("id/{menuSectionId}")
    public MenuSectionDto updateMenuSection(Long menuSectionId, @RequestBody @Valid MenuSectionDtoUpdate menuSectionDtoUpdate) {
        return menuSectionService.updateMenuSection(menuSectionId, menuSectionDtoUpdate);
    }

    @DeleteMapping("id/{menuSectionId}")
    public void deleteMenuSectionById(Long menuSectionId) {
        menuSectionService.deleteSection(menuSectionId);
    }

}
