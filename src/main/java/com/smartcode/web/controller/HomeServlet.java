package com.smartcode.web.controller;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.repository.comment.impl.CommentRepositoryJDBCImpl;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryJDBCImpl;
import com.smartcode.web.repository.user.impl.UserRepositoryJPAImpl;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.service.comment.impl.CommentServiceJDBCImpl;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.service.user.impl.UserServiceJDBCImpl;
import com.smartcode.web.service.user.impl.UserServiceJPAImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        UserRepository userRepository = new UserRepositoryJPAImpl();

        UserService userService = new UserServiceJPAImpl(userRepository);

        User user = userRepository.getByUsername((String)req.getSession().getAttribute("username"));

        Comment comment = new Comment(null, title, description, (int)req.getSession().getAttribute("id"));

        CommentRepository commentRepository = new CommentRepositoryJDBCImpl();

        CommentService commentService = new CommentServiceJDBCImpl(commentRepository);

        if(commentService.get(user, comment.getTitle()) == null) {
            commentService.create(user, comment.getTitle(),comment.getDescription());
        }

        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();

        resp.sendRedirect("login.jsp");

    }






}