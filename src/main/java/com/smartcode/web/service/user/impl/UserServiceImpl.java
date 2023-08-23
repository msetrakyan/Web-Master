package com.smartcode.web.service.user.impl;


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

    public void getAll() throws SQLException {

        connection.setTransactionIsolation(8);
        List<User> all = userRepository.getAll();


    }
}
