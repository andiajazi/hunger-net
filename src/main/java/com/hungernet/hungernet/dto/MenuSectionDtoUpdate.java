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
public class MenuSectionDtoUpdate {

    private String sectionName;
    private Long menuId;

    private List<Long> menuItemIds;

}
