package org.example;

import org.example.dao.OrderDao;
import org.example.dao.OrderDaoImpl;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.Order;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.save(order);
        Order orderFromDb = orderDao.get(1L).orElse(null);

        System.out.println(orderFromDb);

        User bob = new User();
        bob.setLogin("Bob");
        bob.setPassword("1234");
        bob.setOrders(List.of(orderFromDb));
        UserDao userDao = (UserDao) new UserDaoImpl();
        userDao.save(bob);
    }
}