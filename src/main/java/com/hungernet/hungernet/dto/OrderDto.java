package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private Long userId;
    private String clientAddress;

    private Long restaurantId;
    private String restaurantName;

    private OrderStatus orderStatus;
    private LocalDateTime createdAt;

    private List<OrderItemDto> orderItemDtos;

}