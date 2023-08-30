package com.smartcode.web.controller;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.repository.comment.impl.CommentRepositoryImpl;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        UserRepository userRepository = new UserRepositoryImpl();

        User user = userRepository.getByUsername((String)req.getSession().getAttribute("username"));

        Comment comment = new Comment(title, description);

        CommentRepository commentRepository = new CommentRepositoryImpl();

        if(commentRepository.read(user, title) == null) {
            commentRepository.create(user, comment);
        }

    }





}
