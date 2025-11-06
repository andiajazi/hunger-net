package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.converter.RestaurantConverter;
import com.hungernet.hungernet.dto.RestaurantDto;
import com.hungernet.hungernet.dto.RestaurantDtoCreate;
import com.hungernet.hungernet.dto.RestaurantDtoUpdate;
import com.hungernet.hungernet.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants/")
public class RestaurantController {

    private final RestaurantConverter restaurantConverter;
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantConverter restaurantConverter, RestaurantService restaurantService) {
        this.restaurantConverter = restaurantConverter;
        this.restaurantService = restaurantService;
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDto getRestaurantById(@PathVariable("restaurantId") Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping("/{restaurantName}")
    public RestaurantDto getRestaurantByName(@PathVariable("restaurantName") String restaurantName) {
        return restaurantService.getRestaurantByName(restaurantName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public RestaurantDto createRestaurant(@RequestBody @Valid RestaurantDtoCreate restaurantDtoCreate) {
        return restaurantService.createRestaurant(restaurantDtoCreate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{restaurantId}")
    public RestaurantDto updateRestaurantById(@PathVariable("restaurantId") Long restaurantId,
                                              @RequestBody @Valid RestaurantDtoUpdate restaurantDtoUpdate) {
        return restaurantService.updateRestaurantById(restaurantId, restaurantDtoUpdate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{restaurantName}")
    public RestaurantDto updateRestaurantByName(@PathVariable("restaurantName") String restaurantName,
                                              @RequestBody @Valid RestaurantDtoUpdate restaurantDtoUpdate) {
        return restaurantService.updateRestaurantByName(restaurantName, restaurantDtoUpdate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{restaurantId}")
    public void deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{restaurantName}")
    public void deleteRestaurantByName(@PathVariable("restaurantName") String restaurantName) {
        restaurantService.deleteRestaurantByName(restaurantName);
    }

}
