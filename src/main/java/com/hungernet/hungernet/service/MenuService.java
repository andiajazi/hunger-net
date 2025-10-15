package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.MenuConverter;
import com.hungernet.hungernet.converter.MenuItemConverter;
import com.hungernet.hungernet.dto.*;
import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.MenuSection;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.exception.DuplicateResourceException;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuItemRepository;
import com.hungernet.hungernet.repository.MenuRepository;
import com.hungernet.hungernet.repository.MenuSectionRepository;
import com.hungernet.hungernet.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuConverter menuConverter;
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuSectionService menuSectionService;
    private final MenuSectionRepository menuSectionRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuItemConverter menuItemConverter;

    public MenuService(MenuConverter menuConverter, MenuRepository menuRepository, RestaurantRepository restaurantRepository, MenuSectionService menuSectionService, MenuSectionRepository menuSectionRepository, MenuItemRepository menuItemRepository, MenuItemConverter menuItemConverter) {
        this.menuConverter = menuConverter;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuSectionService = menuSectionService;
        this.menuSectionRepository = menuSectionRepository;
        this.menuItemRepository = menuItemRepository;
        this.menuItemConverter = menuItemConverter;
    }

    public MenuDto getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no existing Menu with this id: " +menuId));
        return menuConverter.toDto(menu);
    }

    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream().map(menuConverter::toDto).toList();
    }

    @Transactional
    public MenuDto createMenu(MenuDtoCreate menuDtoCreate) {
        if (menuRepository.findByRestaurant_RestaurantIdAndMenuType(menuDtoCreate.getRestaurantId(), menuDtoCreate.getMenuType()).isPresent()) {
            throw new DuplicateResourceException("There is already a menu of this type: " + menuDtoCreate.getMenuType() + " belonging to the restaurant with id: " + menuDtoCreate.getRestaurantId());
        }

        Restaurant restaurant = restaurantRepository.findById(menuDtoCreate.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no restaurant with this id: " + menuDtoCreate.getRestaurantId()));

        Menu menu = menuConverter.fromCreateDto(menuDtoCreate, restaurant);
        Menu savedMenu = menuRepository.save(menu);
        return menuConverter.toDto(savedMenu);
    }

    @Transactional
    public MenuDto updateMenu(Long menuId, MenuDtoUpdate menuDtoUpdate) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no menu with this id: " + menuId));

        menuConverter.updateEntity(menu, menuDtoUpdate);
        Menu menuSaved = menuRepository.save(menu);
        return menuConverter.toDto(menuSaved);
    }

    @Transactional
    public void deleteMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no existing menu with this id: " + menuId));
        menuRepository.delete(menu);
    }

    public MenuDto getActiveMenuByRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find restaurant with id: " + restaurantId));

        LocalTime now = LocalTime.now();

        Optional<Menu> activeMenu = restaurant.getMenus().stream()
                .filter(menu -> menu.getStartTime().isBefore(now) && menu.getEndTime().isAfter(now))
                .findFirst();

        return activeMenu.map(menuConverter::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("No active menu for restaurant with id: " + restaurantId));
    }

    public List<MenuSectionDto> getSectionsByMenu(Long menuId) {
        return menuSectionService.getMenuSectionsByMenu(menuId);
    }

    @Transactional
    public MenuSectionDto addSectionToMenu(Long menuId, MenuSectionDtoCreate menuSectionDtoCreate) {
        return menuSectionService.addMenuSectionToMenu(menuId, menuSectionDtoCreate);
    }

    @Transactional
    public MenuSectionDto updateMenuSection(Long menuId, MenuSectionDtoUpdate menuSectionDtoUpdate) {
        return menuSectionService.updateMenuSection(menuId, menuSectionDtoUpdate);
    }

    @Transactional
    public void deleteSection(Long menuSectionId) {
        menuSectionService.deleteSection(menuSectionId);
    }

    public List<MenuItemDto> getItemsBysection(Long sectionId){
        MenuSection menuSection = menuSectionRepository.findById(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find section with id: " + sectionId));
        return menuItemRepository.findByMenuSection(menuSection).stream()
                .map(menuItemConverter::toDto).collect(Collectors.toList());
    }

    @Transactional
    public MenuItemDto addItemsToSection(Long sectionId, MenuItemDtoCreate menuItemDtoCreate){
        MenuSection menuSection = menuSectionRepository.findById(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find section with id: " + sectionId));

        MenuItem menuItem = menuItemConverter.fromCreateDto(menuItemDtoCreate, menuSection);
        MenuItem savedItem = menuItemRepository.save(menuItem);
        return menuItemConverter.toDto(savedItem);
    }

    @Transactional
    public MenuItemDto updateMenuItem(Long menuItemId, MenuItemDtoUpdate menuItemDtoUpdate){
        MenuItem existingItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find menu item with id: " +menuItemId));

        menuItemConverter.updateEntity(menuItemDtoUpdate, existingItem);
        MenuItem updatedItem = menuItemRepository.save(existingItem);
        return menuItemConverter.toDto(updatedItem);
    }

    @Transactional
    public void deleteMenuItem(Long menuItemId){
        MenuItem existingItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new ResourceNotFoundException("Could not find menu item with id: " + menuItemId));
        menuItemRepository.delete(existingItem);
    }

}