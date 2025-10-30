package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long restaurantId;
    private String restaurantName;
    private double restaurantRating;
    private String restaurantAddress;
    private String restaurantPhone;

}