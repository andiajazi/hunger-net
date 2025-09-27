package com.hungernet.hungernet.repository;

import com.hungernet.hungernet.entity.Menu;
import com.hungernet.hungernet.enums.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {


    Optional<Menu> findByRestaurant_RestaurantIdAndMenuType(Long restaurantId, MenuType menuType);

}
