package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDtoUpdate {

    private String menuType;
    private LocalTime startTime;
    private LocalTime endTime;
//    private List<MenuSection> menuSections;

}
