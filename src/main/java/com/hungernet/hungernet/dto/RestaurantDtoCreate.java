package com.hungernet.hungernet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDtoCreate {

    @NotNull    @NotBlank
    private String restaurantName;
    @NotNull    @NotBlank
    private String restaurantAddress;
    @NotNull    @NotBlank
    private double restaurantRating;
    @NotNull    @NotBlank
    private String restaurantPhone;

}
