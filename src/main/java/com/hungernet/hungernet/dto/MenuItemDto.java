package com.hungernet.hungernet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {

    private Long menuItemId;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;

    private Long sectionId;
    private List<Long> orderItemIds;

}
