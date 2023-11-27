package com.github.nunompassos.users.integrator.externalapi;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.nunompassos.users.integrator.dto.UserDto;

@Component
public class UserApi {

    @Value("${user.integrator.users.url:http://localhost:9090}")
    private String USER_URL;

    public List<UserDto> getUserByName(final String userName) {
        System.out.println("NMP - " + USER_URL);
        final RestTemplate restTemplate = new RestTemplate();
        final URI targetUrl = UriComponentsBuilder
            .fromUriString(USER_URL + "/api/v1/users")
            .queryParam("name", userName)
            .build()
            .toUri();
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(targetUrl, UserDto[].class);

        return Objects.isNull(response.getBody()) ? List.of() : Arrays.stream(response.getBody()).toList();
    }

}
