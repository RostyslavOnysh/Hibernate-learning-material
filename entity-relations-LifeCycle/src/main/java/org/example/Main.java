package org.example;

import org.example.dao.OrderDao;
import org.example.dao.OrderDaoImpl;
import org.example.dao.ProductDao;
import org.example.dao.ProductDaoImpl;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {

        ProductDao productDao = new ProductDaoImpl();
        Product iphone = new Product();
        iphone.setName("Iphone");
        iphone.setPrice(BigDecimal.valueOf(1489));

        productDao.save(iphone);

        Product productFromDB = productDao.get(1L)
                                          .orElse(null);   // буває помилка якщо прописати тупи ось так    Optional<Product> productFromDB = productDao.get(1L);
        System.out.println(productFromDB);

        User bob = new User();
        bob.setLogin("Bob");
        bob.setPassword("1234");
        UserDao userDao = (UserDao) new UserDaoImpl();
        userDao.save(bob);

        Optional<User> bobFromDB = userDao.get(1L);


        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());

        order.setOwner(bob);
        order.setProducts(List.of(productFromDB));

        OrderDao orderDao = new OrderDaoImpl();
        orderDao.save(order);
        Order orderFromDb = orderDao.get(1L).orElse(null);
        orderFromDb.getProducts().size();

        System.out.println(orderDao.get(1L));
    }
}