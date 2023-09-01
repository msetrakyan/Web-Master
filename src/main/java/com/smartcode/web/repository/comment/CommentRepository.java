package com.smartcode.web.repository.comment;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;

import java.util.List;

public interface CommentRepository {


    Comment create(Comment comment);


    boolean delete(Comment comment);

    void edit(Comment comment);

    List<Comment> getAll(Integer id);






}