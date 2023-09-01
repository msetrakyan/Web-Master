package com.smartcode.web.service.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final Connection connection = DataSource.getInstance().getConnection();
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment get(User user, String title) {

        try {

            PreparedStatement  preparedStatement = connection.prepareStatement("""
            SELECT * FROM comments WHERE title = ? AND users_id = ?
            """);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Comment comment = new Comment(resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("users_id"));
                return comment;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public Comment create(User user, String title, String description) {

        Comment comment = new Comment(title, description, user.getId());

        return commentRepository.create(comment);
    }

    public boolean delete(User user, String title) {

        Comment comment = new Comment(title, "", user.getId());

        return commentRepository.delete(comment);

    }

    public List<Comment> getAll(User user) {

        return commentRepository.getAll(user.getId());

    }







}