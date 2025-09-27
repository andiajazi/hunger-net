package com.hungernet.hungernet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoRequest {

    @NotNull    @NotBlank
    private String clientAddress;
    @NotNull
    private Long userId;
    @NotNull
    private Long restaurantId;
    @NotNull    @NotEmpty
    private List<OrderItemDtoRequest> orderItems;

}