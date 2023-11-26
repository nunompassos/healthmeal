package com.github.nunompassos.products.integrator.externalapi;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.nunompassos.products.integrator.dto.ProductDto;

public class ProductApi {

    private static final String PRODUCT_API = "http://localhost:9091/api/v1/products";

    public static List<ProductDto> getEntryProductsByName(final String name) {
        return getProductsByNameAndDishType(name, ProductDto.DishType.ENTRY);
    }

    public static List<ProductDto> getMainCourseProductsByName(final String name) {
        return getProductsByNameAndDishType(name, ProductDto.DishType.MAIN_COURSE);
    }

    public static List<ProductDto> getBeverageProductsByName(final String name) {
        return getProductsByNameAndDishType(name, ProductDto.DishType.BEVERAGE);
    }

    public static List<ProductDto> getProductsByNameAndDishType(
        final String name,
        final ProductDto.DishType dishType
    ) {
        final RestTemplate restTemplate = new RestTemplate();
        final URI targetUrl = UriComponentsBuilder
            .fromUriString(PRODUCT_API)
            .queryParam("name", name)
            .queryParam("type", dishType)
            .build()
            .toUri();
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(targetUrl, ProductDto[].class);

        return Objects.isNull(response.getBody()) ? List.of() : Arrays.stream(response.getBody()).toList();
    }

}
