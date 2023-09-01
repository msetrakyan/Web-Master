package com.smartcode.web.service.comment;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;

import java.util.List;

public interface CommentService {


    Comment get(User user, String title);

    Comment create(User user, String title, String description);

    boolean delete(User user, String title);

    List<Comment> getAll(User user);
}