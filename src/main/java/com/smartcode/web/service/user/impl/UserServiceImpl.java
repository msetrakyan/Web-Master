package com.smartcode.web.service.user.impl;

import com.smartcode.web.exception.UsernameAlreadyExistsException;
import com.smartcode.web.exception.ValidationException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.utils.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Connection connection = DataSource.getInstance().getConnection();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void transfer(User from, User to, BigDecimal amount) throws SQLException {
        try {
            connection.setAutoCommit(false);
            from.setBalance(from.getBalance().subtract(amount));
            to.setBalance(to.getBalance().add(amount));
            userRepository.update(from);
            userRepository.update(to);
            connection.commit();
        } catch (Throwable e) {
            connection.rollback();
            System.out.println("Rollbacked");
        } finally {
            connection.setAutoCommit(true);
        }
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
        if (!byUsername.getPassword().equals(password)){
            throw new ValidationException("Invalid login or password");
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

    public void getAll() throws SQLException {

        connection.setTransactionIsolation(8);
        List<User> all = userRepository.getAll();

    }
}