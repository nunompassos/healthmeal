package com.github.nunompassos.users.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.nunompassos.users.entities.User;
import com.github.nunompassos.users.integrator.dto.UserDto;
import com.github.nunompassos.users.integrator.dto.UserRequestDto;
import com.github.nunompassos.users.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(final UserRequestDto userDto) {
        final User user = User.builder()
            .name(userDto.name())
            .totalMeals(0)
            .totalCalories(0)
            .build();

        final User savedUser = userRepository.save(user);

        return new UserDto(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getTotalMeals(),
            savedUser.getTotalCalories()
        );
    }

    public UserDto updateUser(
        final String id,
        final UserRequestDto userDto
    ) {
        final User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        user.setName(userDto.name());

        final User savedUser = userRepository.save(user);

        return new UserDto(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getTotalMeals(),
            savedUser.getTotalCalories()
        );
    }

    public UserDto deleteUser(
        final String id
    ) {
        final User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        userRepository.delete(user);

        return new UserDto(
            user.getId(),
            user.getName(),
            user.getTotalMeals(),
            user.getTotalCalories()
        );
    }

    public List<UserDto> listUsers() {
        return userRepository
            .findAll()
            .stream()
            .map(user -> new UserDto(
                user.getId(),
                user.getName(),
                user.getTotalMeals(),
                user.getTotalCalories()
            ))
            .toList();
    }

    public UserDto getUser(
        final String id
    ) {
        final User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        return new UserDto(
            user.getId(),
            user.getName(),
            user.getTotalMeals(),
            user.getTotalCalories()
        );
    }

}
