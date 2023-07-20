package org.example.dao;

import org.example.model.Order;

import java.util.Optional;


public interface OrderDao {
    Order save(Order order);

    Optional<Order> get(Long id);
}
