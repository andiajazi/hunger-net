package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoUpdate {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private Long restaurantId;

}
