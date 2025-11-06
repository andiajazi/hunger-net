package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.dto.UserDto;
import com.hungernet.hungernet.dto.UserDtoCreate;
import com.hungernet.hungernet.dto.UserDtoUpdate;
import com.hungernet.hungernet.enums.Role;
import com.hungernet.hungernet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid UserDtoCreate userDtoCreate) {
        return userService.createUser(userDtoCreate);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER', 'ADMIN')")
    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/role/{role}")
    public List<UserDto> getUsersByRole(@PathVariable("role") Role role) {
        return userService.getUsersByRole(role);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDtoCreate userDtoCreate) {
        return userService.createUser(userDtoCreate);
    }

    @PutMapping("/{username}")
    public UserDto updateUser(@PathVariable("username") String username,
                           @RequestBody @Valid UserDtoUpdate userDtoUpdate) {

        return userService.updateUser(username, userDtoUpdate);

    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
    }

}