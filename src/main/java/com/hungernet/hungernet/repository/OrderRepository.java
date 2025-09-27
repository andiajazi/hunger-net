package com.hungernet.hungernet.repository;

import com.hungernet.hungernet.dto.OrderDto;
import com.hungernet.hungernet.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<OrderDto> findByUserAndRestaurantId(Long userId, Long restaurantId);
}
