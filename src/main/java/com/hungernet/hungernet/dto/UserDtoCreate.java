package com.hungernet.hungernet.dto;

import com.hungernet.hungernet.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoCreate {

    @NotNull @NotBlank
    private String firstName;
    @NotNull @NotBlank
    private String lastName;
    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    private String password;
    @NotNull @NotBlank
    private Role role;
    @NotNull @NotBlank
    private Long restaurantId;

}
