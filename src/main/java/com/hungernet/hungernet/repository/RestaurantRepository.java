package com.hungernet.hungernet.repository;

import com.hungernet.hungernet.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByRestaurantName(String restaurantName);
    Optional<Restaurant> deleteByRestaurantName(String restaurantName);
}
