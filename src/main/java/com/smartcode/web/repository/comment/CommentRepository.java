package com.smartcode.web.repository.comment;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;

import java.util.List;

public interface CommentRepository {


    Comment create(User user, Comment comment);
    String read(User user, String title);
    boolean delete(User user, String title);
    void edit(User user, Comment comment);
    List<Comment> getAll(User user);









}
