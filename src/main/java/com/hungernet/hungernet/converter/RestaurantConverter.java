package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.RestaurantDto;
import com.hungernet.hungernet.dto.RestaurantDtoCreate;
import com.hungernet.hungernet.dto.RestaurantDtoUpdate;
import com.hungernet.hungernet.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantConverter {

    public RestaurantDto toDto(Restaurant restaurant) {

        if (restaurant == null) return null;

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setRestaurantId(restaurant.getRestaurantId());
        restaurantDto.setRestaurantName(restaurant.getRestaurantName());
        restaurantDto.setRestaurantAddress(restaurant.getRestaurantAddress());
        restaurantDto.setRestaurantPhone(restaurant.getRestaurantPhone());
        restaurantDto.setRestaurantRating(restaurant.getRestaurantRating());

        return restaurantDto;

    }

    public Restaurant fromCreateDto(RestaurantDtoCreate restaurantDtoCreate) {

        if (restaurantDtoCreate == null) return null;

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantName(restaurantDtoCreate.getRestaurantName());
        restaurant.setRestaurantRating(restaurantDtoCreate.getRestaurantRating());
        restaurant.setRestaurantAddress(restaurantDtoCreate.getRestaurantAddress());
        restaurant.setRestaurantPhone(restaurantDtoCreate.getRestaurantPhone());

        return restaurant;

    }

    public void updateEntity(Restaurant restaurant, RestaurantDtoUpdate restaurantDtoUpdate) {
        if (restaurantDtoUpdate.getRestaurantName() != null) {
            restaurant.setRestaurantName(restaurantDtoUpdate.getRestaurantName());
        }
        if (restaurantDtoUpdate.getRestaurantRating() != null) {
            restaurant.setRestaurantRating(restaurantDtoUpdate.getRestaurantRating());
        }
        if (restaurantDtoUpdate.getRestaurantAddress() != null) {
            restaurant.setRestaurantAddress(restaurantDtoUpdate.getRestaurantAddress());
        }
        if (restaurantDtoUpdate.getRestaurantPhone() != null) {
            restaurant.setRestaurantPhone(restaurantDtoUpdate.getRestaurantPhone());
        }
    }

}
