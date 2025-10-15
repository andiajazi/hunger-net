package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDtoUpdate {

    private String itemName;
    private String itemDescription;
    private Double itemPrice;
//    private Long sectionId;

}
