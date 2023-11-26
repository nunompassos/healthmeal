package com.github.nunompassos.users.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.*;

import com.github.nunompassos.users.integrator.dto.UserDto;
import com.github.nunompassos.users.integrator.dto.UserRequestDto;
import com.github.nunompassos.users.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody UserDto createUser(
        @RequestBody final UserRequestDto requestDto
    ) {
        return service.createUser(requestDto);
    }

    @PutMapping("/{id}")
    public @ResponseBody UserDto updateUser(
        @PathVariable final String id,
        @RequestBody final UserRequestDto requestDto
    ) {
        return service.updateUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody UserDto deleteUser(
        @PathVariable final String id
    ) {
        return service.deleteUser(id);
    }

    @GetMapping
    public @ResponseBody List<UserDto> listUsers(
        @RequestParam(required = false) final String name
    ) {
        return Objects.isNull(name) ? service.listUsers() : List.of(service.getUserByName(name));
    }

    @GetMapping("/{id}")
    public @ResponseBody UserDto getUser(
        @PathVariable final String id
    ) {
        return service.getUser(id);
    }

}
