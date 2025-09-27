package com.hungernet.hungernet.converter;

import com.hungernet.hungernet.dto.UserDtoCreate;
import com.hungernet.hungernet.dto.UserDto;
import com.hungernet.hungernet.dto.UserDtoUpdate;
import com.hungernet.hungernet.entity.Restaurant;
import com.hungernet.hungernet.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto toDto(User user) {
        if (user ==  null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());

        if (user.getRestaurant() != null) {
            userDto.setRestaurantId(user.getRestaurant().getRestaurantId());
        }

        return userDto;
    }

    public User fromCreateDto(UserDtoCreate userDtoCreate, Restaurant restaurant) {
        if (userDtoCreate == null)
            return null;

        User user = new User();
        user.setFirstName(userDtoCreate.getFirstName());
        user.setLastName(userDtoCreate.getLastName());
        user.setUsername(userDtoCreate.getUsername());
        user.setPassword(userDtoCreate.getPassword());
        user.setRole(userDtoCreate.getRole());
        user.setRestaurant(restaurant);

        return user;
    }

    public void updateEntity(User user, UserDtoUpdate userDtoUpdate, Restaurant restaurant) {
        if (userDtoUpdate.getFirstName() != null) {
            user.setFirstName(userDtoUpdate.getFirstName());
        }

        if (userDtoUpdate.getLastName() != null) {
            user.setLastName(userDtoUpdate.getLastName());
        }

        if (userDtoUpdate.getPassword() != null) {
            user.setPassword(userDtoUpdate.getPassword());
        }

        if (userDtoUpdate.getRole() != null) {
            user.setRole(userDtoUpdate.getRole());
        }

        if (restaurant != null) {
            user.setRestaurant(restaurant);
        }

    }

}
