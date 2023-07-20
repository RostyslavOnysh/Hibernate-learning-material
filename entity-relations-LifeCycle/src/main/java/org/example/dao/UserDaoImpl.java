package org.example.dao;

import org.example.exception.DataProcessingException;
import org.example.model.Order;
import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public User save(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("error");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = null;
            try {
                session = sessionFactory.openSession();
                return Optional.ofNullable(session.get(User.class, id));
            } catch (RuntimeException e) {
                throw new DataProcessingException("Error");
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }
