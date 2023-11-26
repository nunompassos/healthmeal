package com.github.nunompassos.orders.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.*;

import com.github.nunompassos.orders.integrator.dto.OrderDto;
import com.github.nunompassos.orders.integrator.dto.OrderRequestDto;
import com.github.nunompassos.orders.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(final OrderService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody OrderDto createOrder(
        @RequestBody final OrderRequestDto request
        ) {
        return service.createOrder(request);
    }

    @PutMapping("/{id}")
    public @ResponseBody OrderDto updateOrder(
        @PathVariable final String id,
        @RequestBody final OrderRequestDto request
    ) {
        return service.updateOrder(id, request);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody OrderDto deleteOrder(
        @PathVariable final String id
    ) {
        return service.deleteOrder(id);
    }

    @GetMapping
    public @ResponseBody List<OrderDto> listOrders(
        @RequestParam(required = false) final String userId
    ) {
        return Objects.isNull(userId) ? service.listOrders() : service.getOrderByUserId(userId);
    }

    @GetMapping("/{id}")
    public @ResponseBody OrderDto getOrder(
        @PathVariable final String id
    ) {
        return service.getOrder(id);
    }

}
