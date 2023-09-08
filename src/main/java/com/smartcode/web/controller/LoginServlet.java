package com.smartcode.web.controller;

import com.smartcode.web.exception.ValidationException;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryJDBCImpl;
import com.smartcode.web.repository.user.impl.UserRepositoryJPAImpl;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.service.user.impl.UserServiceJDBCImpl;
import com.smartcode.web.service.user.impl.UserServiceJPAImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepositoryJPAImpl();
    private final UserService userService = new UserServiceJPAImpl(userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {


            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String rememberMe = req.getParameter("rememberMe");

            if(rememberMe != null) {
                Cookie cookie = new Cookie("usernamePassword", username + ":" +password);
                resp.addCookie(cookie);
            }

            System.out.println("Before");

            userService.login(username, password);

            System.out.println("after");
            req.getSession().setAttribute("username", username);

            req.getSession().setAttribute("id", userRepository.getByUsername(username).getId());

            req.getSession().setMaxInactiveInterval(36000);
            req.getRequestDispatcher("home.jsp").forward(req, resp);

        } catch (ValidationException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }


    }


}
