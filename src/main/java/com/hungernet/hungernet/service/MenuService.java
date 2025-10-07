package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.MenuConverter;
import com.hungernet.hungernet.dto.MenuDto;
import com.hungernet.hungernet.dto.MenuDtoCreate;
import com.hungernet.hungernet.dto.MenuDtoUpdate;
import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.exception.DuplicateResourceException;
import com.hungernet.hungernet.exception.ResourceNotFoundException;
import com.hungernet.hungernet.repository.MenuRepository;
import com.hungernet.hungernet.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuConverter menuConverter;
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuConverter menuConverter, MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuConverter = menuConverter;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
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

}
