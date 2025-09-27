package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDtoUpdate {

    private String clientAddress;
    private OrderStatus orderStatus;

    private Long userId;
    private Long restaurantId;
    private List<OrderItemDtoRequest> orderItems;

}