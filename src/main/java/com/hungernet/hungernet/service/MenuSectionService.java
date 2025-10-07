package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.MenuSectionConverter;
import com.hungernet.hungernet.dto.MenuSectionDto;
import com.hungernet.hungernet.dto.MenuSectionDtoCreate;
import com.hungernet.hungernet.dto.MenuSectionDtoUpdate;
import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.MenuSection;
import com.hungernet.hungernet.exception.DuplicateResourceException;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.MenuRepository;
import com.hungernet.hungernet.repository.MenuSectionRepository;
import com.hungernet.hungernet.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuSectionService {

    private final MenuSectionConverter menuSectionConverter;
    private final MenuSectionRepository menuSectionRepository;
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuSectionService(MenuSectionConverter menuSectionConverter, MenuSectionRepository menuSectionRepository, MenuRepository menuRepository, RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.menuSectionConverter = menuSectionConverter;
        this.menuSectionRepository = menuSectionRepository;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public MenuSectionDto getMenuSectionById(Long menuSectionId) {
        MenuSection menuSection = menuSectionRepository.findById(menuSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu section with this id: " + menuSectionId));

        return menuSectionConverter.toDto(menuSection);
    }

    public List<MenuSectionDto> getAllMenuSections() {
        return menuSectionRepository.findAll().stream().map(menuSectionConverter::toDto).toList();
    }

    @Transactional
    public MenuSectionDto createMenuSection(MenuSectionDtoCreate menuSectionDtoCreate) {

        if (menuSectionRepository.findMenuSectionByMenuIdAndSectionName(menuSectionDtoCreate.getMenuId(), menuSectionDtoCreate.getSectionName()).isPresent()) {
            throw new DuplicateResourceException("There is already a menu section with this name: " + menuSectionDtoCreate.getSectionName()+ " belonging to the menu with this id: " + menuSectionDtoCreate.getMenuId());
        }

        Menu menu = menuRepository.findById(menuSectionDtoCreate.getMenuId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu with this id: " + menuSectionDtoCreate.getMenuId()));

        MenuSection menuSection = menuSectionConverter.fromCreateDto(menuSectionDtoCreate, menu);
        MenuSection savedMenuSection = menuSectionRepository.save(menuSection);

        return menuSectionConverter.toDto(savedMenuSection);

    }

    @Transactional
    public MenuSectionDto updateMenuSection(Long menuSectionId, MenuSectionDtoUpdate menuSectionDtoUpdate) {
        MenuSection menuSection = menuSectionRepository.findById(menuSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuSection not found with id: " + menuSectionId));
        Menu menu = menuRepository.findById(menuSectionDtoUpdate.getMenuId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu with this id: " + menuSectionDtoUpdate.getMenuId()));

        List<MenuItem> menuItems = null;
        if (menuSectionDtoUpdate.getMenuItemIds() != null) {
            menuItems = menuItemRepository.findAllById(menuSectionDtoUpdate.getMenuItemIds());
        }

        menuSectionConverter.updateEntity(menuSection, menuSectionDtoUpdate, menu, menuItems);
        MenuSection updatedSection = menuSectionRepository.save(menuSection);
        return menuSectionConverter.toDto(updatedSection);
    }

    @Transactional
    public void deleteSection(Long sectionId) {
        MenuSection section = menuSectionRepository.findById(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuSection not found with id: " + sectionId));
        menuSectionRepository.delete(section);
    }

}
