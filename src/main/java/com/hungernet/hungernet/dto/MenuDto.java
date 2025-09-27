package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.enums.MenuType;
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
public class MenuDto {

    private Long menuId;
    private MenuType menuType;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isActive;

    private Long restaurantId;
    private List<Long> menuSectionIds;

}