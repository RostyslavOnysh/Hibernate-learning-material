package org.example;

import org.example.dao.OrderDao;
import org.example.dao.OrderDaoImpl;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.Order;
import org.example.model.User;

import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        User bob = new User();
        bob.setLogin("Bob");
        bob.setPassword("1234");
        UserDao userDao = (UserDao) new UserDaoImpl();
        User bobikFromDb = userDao.save(bob);

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOwner(bobikFromDb);
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.save(order);

        System.out.println(orderDao.get(1L));
    }
}