package com.smartcode.web.controller;

import com.smartcode.web.exception.ValidationException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.service.user.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {



            UserRepository userRepository = new UserRepositoryImpl();

            UserService userService = new UserServiceImpl(userRepository);


            User user = userRepository.getById((Integer)req.getSession().getAttribute("id"));


            userService.changePassword(user, req.getParameter("current"), req.getParameter("new"));



           req.getRequestDispatcher("home.jsp").forward(req, resp);


        } catch (ValidationException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
        }


    }







}
