package com.github.nunompassos.users.integrator.externalapi;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.nunompassos.users.integrator.dto.UserDto;

public class UserAPI {

    private static final String USER_URL = "http://localhost:9090/api/v1/users";

    public static List<UserDto> getUserByName(final String userName) {
        final RestTemplate restTemplate = new RestTemplate();
        final URI targetUrl = UriComponentsBuilder
            .fromUriString(USER_URL)
            .queryParam("name", userName)
            .build()
            .toUri();
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(targetUrl, UserDto[].class);

        return Objects.isNull(response.getBody()) ? List.of() : Arrays.stream(response.getBody()).toList();
    }

}
