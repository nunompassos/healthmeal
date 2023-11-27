package com.github.nunompassos.products.integrator.externalapi;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.nunompassos.products.integrator.dto.ProductDto;

@Component
public class ProductApi {

    @Value("${products.integrator.products.url:http://localhost:9090}")
    private String PRODUCT_API;

    public List<ProductDto> getEntryProductsByName(final String name) {
        return getProductsByNameAndDishType(name, ProductDto.DishType.ENTRY);
    }

    public List<ProductDto> getMainCourseProductsByName(final String name) {
        return getProductsByNameAndDishType(name, ProductDto.DishType.MAIN_COURSE);
    }

    public List<ProductDto> getBeverageProductsByName(final String name) {
        return getProductsByNameAndDishType(name, ProductDto.DishType.BEVERAGE);
    }

    public List<ProductDto> getProductsByNameAndDishType(
        final String name,
        final ProductDto.DishType dishType
    ) {
        System.out.println("NMP - " + PRODUCT_API);
        final RestTemplate restTemplate = new RestTemplate();
        final URI targetUrl = UriComponentsBuilder
            .fromUriString(PRODUCT_API + "/api/v1/products")
            .queryParam("name", name)
            .queryParam("type", dishType)
            .build()
            .toUri();
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(targetUrl, ProductDto[].class);

        return Objects.isNull(response.getBody()) ? List.of() : Arrays.stream(response.getBody()).toList();
    }

}
