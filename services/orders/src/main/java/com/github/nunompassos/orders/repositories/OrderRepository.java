package com.github.nunompassos.orders.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.nunompassos.orders.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUserId(final String userId);
}
