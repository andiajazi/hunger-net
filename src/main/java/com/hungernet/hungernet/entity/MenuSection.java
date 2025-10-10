package com.hungernet.hungernet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuSectionId;

    private String sectionName;

    @ManyToOne
    private Menu menu;

    @OneToMany(mappedBy = "menuSection")
    private List<MenuItem> menuItems;

}