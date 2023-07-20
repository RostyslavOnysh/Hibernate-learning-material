package org.example.dao;

import org.example.model.Product;
import org.example.model.User;

import java.util.Optional;

public interface ProductDao {

    Product save(Product product);
    Optional<Product> get(Long id);
}
