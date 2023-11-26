package com.github.nunompassos.users.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.github.nunompassos.users.integrator.dto.UserDto;
import com.github.nunompassos.users.integrator.dto.UserRequestDto;
import com.github.nunompassos.users.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(
        @RequestBody final UserRequestDto requestDto
    ) {
        return userService.createUser(requestDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(
        @PathVariable final String id,
        @RequestBody final UserRequestDto requestDto
    ) {
        return userService.updateUser(id, requestDto);
    }


    @DeleteMapping("/{id}")
    public UserDto deleteUser(
        @PathVariable final String id
    ) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public List<UserDto> listUsers() {
        return userService.listUsers();
    }


    @GetMapping("/{id}")
    public UserDto getUser(
        @PathVariable final String id
    ) {
        return userService.getUser(id);
    }

}
