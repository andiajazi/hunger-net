package com.hungernet.hungernet.entity;

import com.hungernet.hungernet.enums.Role;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
//    private int restaurantId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}