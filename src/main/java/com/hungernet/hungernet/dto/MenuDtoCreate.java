package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.enums.MenuType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDtoCreate {

    @NotNull @NotBlank
    private MenuType menuType;
    @NotNull @NotBlank
    private LocalTime startTime;
    @NotNull @NotBlank
    private LocalTime endTime;
    @NotNull @NotBlank
    private Long restaurantId;

    private List<MenuSectionDtoCreate> menuSections;

}
