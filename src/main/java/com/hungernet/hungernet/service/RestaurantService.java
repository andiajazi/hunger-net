package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.RestaurantConverter;
import com.hungernet.hungernet.dto.RestaurantDto;
import com.hungernet.hungernet.dto.RestaurantDtoCreate;
import com.hungernet.hungernet.dto.RestaurantDtoUpdate;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantConverter restaurantConverter;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantConverter restaurantConverter) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantConverter = restaurantConverter;
    }

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurantConverter::toDto).toList();
    }

    public RestaurantDto getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Could not find restaurant with id: " +restaurantId));

        return restaurantConverter.toDto(restaurant);
    }

    public RestaurantDto getRestaurantByName(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() -> new RuntimeException("Could not find restaurant with name: " + restaurantName));

        return restaurantConverter.toDto(restaurant);
    }

    public RestaurantDto createRestaurant(RestaurantDtoCreate restaurantDtoCreate) {

        if (restaurantRepository.findByRestaurantName(restaurantDtoCreate.getRestaurantName()).isPresent()) {
            throw new RuntimeException("There is already a Restaurant with this name: " +restaurantDtoCreate.getRestaurantName());
        }

        Restaurant restaurant = restaurantConverter.fromCreateDto(restaurantDtoCreate);
        Restaurant saved = restaurantRepository.save(restaurant);

        return restaurantConverter.toDto(saved);

    }

    public RestaurantDto updateRestaurantById(Long id, RestaurantDtoUpdate restaurantDtoUpdate) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        restaurantConverter.updateEntity(restaurant, restaurantDtoUpdate);
        Restaurant updated = restaurantRepository.save(restaurant);

        return restaurantConverter.toDto(updated);
    }

    public RestaurantDto updateRestaurantByName(String restaurantName, RestaurantDtoUpdate restaurantDtoUpdate) {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with name: " + restaurantName));

        restaurantConverter.updateEntity(restaurant, restaurantDtoUpdate);
        Restaurant updated = restaurantRepository.save(restaurant);

        return restaurantConverter.toDto(updated);
    }

    public void deleteRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with Id: " + restaurantId));
        restaurantRepository.delete(restaurant);
    }

    public void deleteRestaurantByName(String restaurantName) {
        restaurantRepository.deleteByRestaurantName(restaurantName);
    }

}
