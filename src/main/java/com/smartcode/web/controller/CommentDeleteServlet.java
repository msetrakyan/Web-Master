package com.smartcode.web.controller;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.repository.comment.impl.CommentRepositoryImpl;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.service.comment.impl.CommentServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentDeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentRepository commentRepository = new CommentRepositoryImpl();

        CommentService commentService = new CommentServiceImpl(commentRepository);

        String title = req.getParameter("title");

        UserRepository userRepository = new UserRepositoryImpl();

        User user = userRepository.getById((int)req.getSession().getAttribute("id"));

        Comment comment = commentService.get(user, title);

        commentRepository.delete(comment);

        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }




}
