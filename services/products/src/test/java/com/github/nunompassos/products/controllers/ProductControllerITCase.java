package com.github.nunompassos.products.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nunompassos.products.entities.Product;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerITCase {
    @Autowired private MockMvc mvc;
    @Autowired private ProductRepository repository;

    private final static String URL = "/api/v1/products";
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static String PRODUCT_NAME = "product-name";
    private final static String DISH_TYPE = "MAIN_COURSE";
    private final static int CALORIES = 100;

    private final static String PRODUCT_CREATION_REQUEST = "{\"name\": \"" +
        PRODUCT_NAME +
        "\", \"dishType\": \"" +
        DISH_TYPE +
        "\", \"calories\": " +
        CALORIES +
        "}";

    @Test
    public void createProduct_happyPath() throws Exception {
        final MvcResult result = mvc
            .perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(PRODUCT_CREATION_REQUEST))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(PRODUCT_NAME))
            .andExpect(jsonPath("$.dishType").value(DISH_TYPE))
            .andExpect(jsonPath("$.calories").value(CALORIES))
            .andReturn();

        final ProductDto product = OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ProductDto.class);
        final Optional<Product> productFromDb = repository.findById(product.id());

        assertThat(productFromDb).isPresent();
        assertEquals(product.id(), productFromDb.get().getId(), "Database ID should be the same as the one returned");
        assertEquals(PRODUCT_NAME, productFromDb.get().getName(), "Product name should be " + PRODUCT_NAME);
        assertEquals(DISH_TYPE, productFromDb.get().getType().name(), "Dish Type should be " + DISH_TYPE);
        assertEquals(CALORIES, productFromDb.get().getCalories(), "Calories should be " + CALORIES);
    }

    @Test
    public void createProduct_missingName_returns400() throws Exception {
        final String request = "{\"dishType\": \"" + DISH_TYPE + "\", \"calories\": " + CALORIES + "}";
        mvc
            .perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(request))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();
    }
}
