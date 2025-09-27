package com.hungernet.hungernet.entity;

import com.hungernet.hungernet.enums.MenuType;
import jakarta.persistence.*;
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
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;


    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isActive;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuSection> menuSections;

    public boolean isActive() {
        LocalTime now = LocalTime.now();
        return  !now.isBefore(startTime) && now.isBefore(endTime);
    }

}