package com.smartcode.web.controller;

import com.smartcode.web.model.Comment;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.repository.comment.impl.CommentRepositoryImpl;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CommentServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommentRepository commentRepository = new CommentRepositoryImpl();

        UserRepository userRepository = new UserRepositoryImpl();

        String username = (String)req.getSession().getAttribute("username");

        List<Comment> list = commentRepository.getAll(userRepository.getByUsername(username));

        PrintWriter printWriter = resp.getWriter();

        for(int i = 0; i < list.size(); i++) {
          printWriter.print(list.get(i));
        }

    }
}
