package com.hungernet.hungernet.controller;

import com.hungernet.hungernet.dto.AuthRequest;
import com.hungernet.hungernet.dto.AuthResponse;
import com.hungernet.hungernet.dto.UserDto;
import com.hungernet.hungernet.dto.UserDtoCreate;
import com.hungernet.hungernet.service.AuthService;
import com.hungernet.hungernet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.authenticate(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDtoCreate userDtoCreate) {
        UserDto createdUser = userService.createUser(userDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
