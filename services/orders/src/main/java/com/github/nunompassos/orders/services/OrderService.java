package com.github.nunompassos.orders.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.nunompassos.orders.entities.Order;
import com.github.nunompassos.orders.integrator.dto.OrderDto;
import com.github.nunompassos.orders.integrator.dto.OrderRequestDto;
import com.github.nunompassos.orders.repositories.OrderRepository;
import com.github.nunompassos.products.integrator.dto.ProductDto;
import com.github.nunompassos.products.integrator.externalapi.ProductApi;
import com.github.nunompassos.users.integrator.dto.UserDto;
import com.github.nunompassos.users.integrator.externalapi.UserApi;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ProductApi productApi;
    private final UserApi userApi;

    public OrderService(
        OrderRepository repository,
        ProductApi productApi,
        UserApi userApi
    ) {
        this.repository = repository;
        this.productApi = productApi;
        this.userApi = userApi;
    }

    public OrderDto createOrder(OrderRequestDto requestDto) {
        final Order order = createOrderEntity(requestDto);

        return repository.save(order).toDto();
    }

    public OrderDto updateOrder(
        final String orderId,
        final OrderRequestDto requestDto
    ) {
        final Order order = repository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(orderId));
        final Order newOrder = createOrderEntity(requestDto);

        order.setEntry(newOrder.getEntry());
        order.setEntryCalories(newOrder.getEntryCalories());
        order.setMainCourse(newOrder.getMainCourse());
        order.setMainCourseCalories(newOrder.getMainCourseCalories());
        order.setBeverage(newOrder.getBeverage());
        order.setBeverageCalories(newOrder.getBeverageCalories());

        return repository.save(order).toDto();
    }

    public OrderDto deleteOrder(final String orderId) {
        final Order order = repository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(orderId));

        repository.delete(order);

        return order.toDto();
    }

    public List<OrderDto> listOrders() {
        return repository
            .findAll()
            .stream()
            .map(Order::toDto)
            .toList();
    }

    public OrderDto getOrder(final String orderId) {
        return repository
            .findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException(orderId))
            .toDto();
    }

    public List<OrderDto> getOrderByUserId(final String userId) {
        return repository
            .findByUserId(userId)
            .stream()
            .map(Order::toDto)
            .toList();
    }

    private Order createOrderEntity(final OrderRequestDto requestDto) {
        final UserDto user = userApi.getUserByName(requestDto.userName()).get(0);
        final ProductDto entry = productApi.getEntryProductsByName(requestDto.entry()).get(0);
        final ProductDto mainCourse = productApi.getMainCourseProductsByName(requestDto.mainCourse()).get(0);
        final ProductDto beverage = productApi.getBeverageProductsByName(requestDto.beverage()).get(0);

        return Order
            .builder()
            .userId(user.id())
            .userName(user.name())
            .entry(entry.name())
            .entryCalories(entry.calories())
            .mainCourse(mainCourse.name())
            .mainCourseCalories(mainCourse.calories())
            .beverage(beverage.name())
            .beverageCalories(beverage.calories())
            .build();
    }

}
