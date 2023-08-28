package com.smartcode.web.repository.user;


import com.smartcode.web.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user);

    void update(User user);

    List<User> getAll();

    User getById(Integer id);

    void delete(Integer id);

    List<User> findByName(String string);

    User getByUsername(String username);
}
