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
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto createUser(final UserRequestDto userDto) {
        final User user = User.builder()
            .name(userDto.name())
            .totalMeals(0)
            .totalCalories(0)
            .build();

        return repository.save(user).toDto();
    }

    public UserDto updateUser(
        final String id,
        final UserRequestDto userDto
    ) {
        final User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        user.setName(userDto.name());

        return repository.save(user).toDto();
    }

    public UserDto deleteUser(
        final String id
    ) {
        final User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        repository.delete(user);

        return user.toDto();
    }

    public List<UserDto> listUsers() {
        return repository
            .findAll()
            .stream()
            .map(User::toDto)
            .toList();
    }

    public UserDto getUser(
        final String id
    ) {
        return repository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id))
            .toDto();
    }

    public UserDto getUserByName(
        String name
    ) {
        return repository
            .findByName(name)
            .orElseThrow(() -> new EntityNotFoundException(name))
            .toDto();
    }

}
