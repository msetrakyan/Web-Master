package com.smartcode.web.controller;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.repository.comment.impl.CommentRepositoryImpl;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.service.comment.impl.CommentServiceImpl;
import com.smartcode.web.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CommentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentRepository commentRepository = new CommentRepositoryImpl();

        CommentService commentService = new CommentServiceImpl(commentRepository);

        UserRepository userRepository = new UserRepositoryImpl();

        User user = userRepository.getById((Integer)req.getSession().getAttribute("id"));

        System.out.println(req.getParameter("title"));

        commentService.delete(user, req.getParameter("title"));

        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentRepository commentRepository = new CommentRepositoryImpl();

        CommentService commentService = new CommentServiceImpl(commentRepository);

        req.getSession().setAttribute("title", req.getParameter("title"));

        req.getRequestDispatcher("commentUpdate.jsp").forward(req, resp);

    }



}