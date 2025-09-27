package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDtoRequest {

    private Long menuItemId;
    private Integer quantity;

}
