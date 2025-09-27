package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.entity.MenuSection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
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
