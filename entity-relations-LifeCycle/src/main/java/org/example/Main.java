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

        User bob = new User();
        bob.setLogin("Bob");
        bob.setPassword("1234");
        UserDao userDao = (UserDao) new UserDaoImpl();
        userDao.save(bob);



        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOwner(bob);
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.save(order);
        Order orderFromDb = orderDao.get(1L).orElse(null);




    }
}