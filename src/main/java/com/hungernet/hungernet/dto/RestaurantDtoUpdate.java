package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDtoUpdate {

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhone;
    private Double restaurantRating;
    private List<Long> managerIds;

}
