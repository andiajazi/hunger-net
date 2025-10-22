package com.hungernet.hungernet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;

    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;

    @ManyToOne
    private MenuSection menuSection;

    @OneToMany(mappedBy = "menuItem")
    private List<OrderItem> orderItems;

}