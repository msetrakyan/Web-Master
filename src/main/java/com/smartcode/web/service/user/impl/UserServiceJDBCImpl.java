package com.smartcode.web.service.user.impl;

import com.smartcode.web.exception.UsernameAlreadyExistsException;
import com.smartcode.web.exception.ValidationException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.utils.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceJDBCImpl implements UserService {

    private final UserRepository userRepository;

    private final Connection connection = DataSource.getInstance().getConnection();

    public UserServiceJDBCImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void register(User user) {
        try {
            connection.setAutoCommit(false);
            validateUserRegistration(user);
            userRepository.create(user);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void login(String username, String password) {
        User byUsername = userRepository.getByUsername(username);
        if(byUsername == null) {
            throw new ValidationException("Login doesn't exist");
        }
        else if(!byUsername.getPassword().equals(password)){
            throw new ValidationException("Wrong password");
        }

    }

    private void validateUserRegistration(User user) {
        if (userRepository.getByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException(String.format("User by username: %s already exists", user.getUsername()));
        }
        if (user.getPassword().length() < 8){
            throw new ValidationException("Password must be more than 8 symbols");
        }

    }

    public boolean changePassword(User user, String oldPass, String newPass) {
         return userRepository.changePassword(user, oldPass, newPass);
    }



}