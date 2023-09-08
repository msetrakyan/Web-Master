package com.smartcode.web.service.user.impl;

import com.smartcode.web.exception.ResourceNotFoundException;
import com.smartcode.web.exception.ValidationException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.*;
import java.sql.SQLException;

public class UserServiceJPAImpl implements UserService {

    private UserRepository userRepository;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserServiceJPAImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {

        Session session = sessionFactory.openSession();

        Transaction transaction = (Transaction) session.beginTransaction();

        session.save(user);

        try {
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }


    public void login(String username, String password) {

        User user = userRepository.getByUsername(username);

        if(user == null) {
            throw new ResourceNotFoundException("Username doesn't exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new ValidationException("Wrong password");
        }
    }

    public boolean changePassword(User user, String oldPass, String newPass) {

        Session session = sessionFactory.openSession();

        org.hibernate.Transaction transaction1 = session.beginTransaction();
        Transaction transaction = (Transaction) transaction1;

        if(user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            userRepository.update(user);
            session.close();
            return true;
        } else {
            return false;
        }

    }




}
