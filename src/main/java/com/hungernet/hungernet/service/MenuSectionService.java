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
import java.util.stream.Collectors;

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

    public List<MenuSectionDto> getMenuSectionsByMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu with this id: " + menuId));
        List<MenuSection> menuSections = menuSectionRepository.findMenuSectionByMenu(menu);
        return menuSections.stream().map(menuSectionConverter::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MenuSectionDto addMenuSectionToMenu(Long menuId, MenuSectionDtoCreate menuSectionDtoCreate) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(()-> new ResourceNotFoundException("Could not find menu with id: " + menuId));

        MenuSection menuSection  = menuSectionConverter.fromCreateDto(menuSectionDtoCreate,menu);
        MenuSection savedSection = menuSectionRepository.save(menuSection);

        return menuSectionConverter.toDto(savedSection);
    }

    @Transactional
    public MenuSectionDto updateMenuSection(Long menuSectionId, MenuSectionDtoUpdate menuSectionDtoUpdate) {
        MenuSection existingSection = menuSectionRepository.findById(menuSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu section with this id: " +menuSectionId));

        menuSectionConverter.updateEntity(existingSection, menuSectionDtoUpdate);
        MenuSection updatedMenuSection = menuSectionRepository.save(existingSection);

        return menuSectionConverter.toDto(updatedMenuSection);
    }

    public MenuSectionDto getMenuSectionById(Long menuSectionId) {
        MenuSection menuSection = menuSectionRepository.findById(menuSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu section with this id: " + menuSectionId));

        return menuSectionConverter.toDto(menuSection);
    }

    @Transactional
    public void deleteSection(Long sectionId) {
        MenuSection section = menuSectionRepository.findById(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException("MenuSection not found with id: " + sectionId));
        menuSectionRepository.delete(section);
    }

}
