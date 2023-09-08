package com.smartcode.web.controller;

import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.repository.comment.impl.CommentRepositoryJDBCImpl;
import com.smartcode.web.repository.comment.impl.CommentRepositoryJPAImpl;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryJDBCImpl;
import com.smartcode.web.repository.user.impl.UserRepositoryJPAImpl;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.service.comment.impl.CommentServiceJDBCImpl;
import com.smartcode.web.service.comment.impl.CommentServiceJPAImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentRepository commentRepository = new CommentRepositoryJPAImpl();

        CommentService commentService = new CommentServiceJPAImpl(commentRepository);

        UserRepository userRepository = new UserRepositoryJPAImpl();

        User user = userRepository.getById((Integer)req.getSession().getAttribute("id"));

        System.out.println(req.getParameter("title"));

        commentService.delete(user, req.getParameter("title"));

        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentRepository commentRepository = new CommentRepositoryJDBCImpl();

        CommentService commentService = new CommentServiceJDBCImpl(commentRepository);

        req.getSession().setAttribute("title", req.getParameter("title"));

        req.getRequestDispatcher("commentUpdate.jsp").forward(req, resp);

    }



}