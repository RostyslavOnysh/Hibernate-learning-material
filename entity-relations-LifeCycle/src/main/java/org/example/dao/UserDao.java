package org.example.dao;

import org.example.model.Order;
import org.example.model.User;

import java.util.Optional;

public interface UserDao {
    User save(User user);

    Optional<User> get(Long id);
}

