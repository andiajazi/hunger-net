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
public class MenuItemDtoCreate {

    @NotNull @NotBlank
    private String itemName;
    @NotNull @NotBlank
    private String itemDescription;
    @NotNull @NotBlank
    private double itemPrice;
    @NotNull @NotBlank
    private Long sectionId;

}