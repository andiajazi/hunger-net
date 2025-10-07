package com.hungernet.hungernet.service;

import com.hungernet.hungernet.converter.UserConverter;
import com.hungernet.hungernet.dto.UserDtoCreate;
import com.hungernet.hungernet.dto.UserDto;
import com.hungernet.hungernet.dto.UserDtoUpdate;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.entity.User;
import com.hungernet.hungernet.enums.Role;
import com.hungernet.hungernet.repository.RestaurantRepository;
import com.hungernet.hungernet.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter userConverter, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userConverter::toDto).toList();
    }

    public List<UserDto> getUsersByRole(Role role) {
        return userRepository.findByRole(role)
                .stream().map(userConverter::toDto).toList();

    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return userConverter.toDto(user);
    }

    @Transactional
    public UserDto createUser(UserDtoCreate userDtoCreate) {

        if (userRepository.findByUsername(userDtoCreate.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!" + userDtoCreate.getUsername());
        }

        Restaurant restaurant = null;
        if (userDtoCreate.getRole() == Role.RESTAURANT_MANAGER) {
            restaurant = restaurantRepository.findById(userDtoCreate.getRestaurantId())
                    .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + userDtoCreate.getRestaurantId()));
        }

        userDtoCreate.setPassword(passwordEncoder.encode(userDtoCreate.getPassword()));

        User user = userConverter.fromCreateDto(userDtoCreate, restaurant);

        User savedUser = userRepository.save(user);
        return userConverter.toDto(savedUser);

    }

    @Transactional
    public UserDto updateUser(String username, UserDtoUpdate userDtoUpdate) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("There is now user with this username: " + username));

        Restaurant restaurant = null;

        if (userDtoUpdate.getRole() == Role.RESTAURANT_MANAGER && userDtoUpdate.getRestaurantId() != null) {
            restaurant = restaurantRepository.findById(userDtoUpdate.getRestaurantId())
                    .orElseThrow(() -> new RuntimeException("Restaurant not found with id: "  + userDtoUpdate.getRestaurantId()));
        }

        if (userDtoUpdate.getPassword() != null && !userDtoUpdate.getPassword().isEmpty()) {
            userDtoUpdate.setPassword(passwordEncoder.encode(userDtoUpdate.getPassword()));
        }

        userConverter.updateEntity(user, userDtoUpdate, restaurant);

        User updated = userRepository.save(user);

        return userConverter.toDto(updated);

    }

    @Transactional
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Could not find a user with username: " +username));

        userRepository.delete(user);
    }

}
