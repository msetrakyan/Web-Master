package com.smartcode.web.service.user;


import com.smartcode.web.model.User;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface UserService {



    void register(User user);

    void login(String username, String password);

    boolean changePassword(User user, String oldPass, String newPass);



}
