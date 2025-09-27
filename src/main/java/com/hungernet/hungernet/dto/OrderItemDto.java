package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long orderItemId;
    private Integer quantity;
    private BigDecimal itemPrice;

    private Long orderId;

    private Long menuItemId;
    private String menuItemName;

}