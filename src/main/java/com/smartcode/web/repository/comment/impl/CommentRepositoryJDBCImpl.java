package com.smartcode.web.repository.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryJDBCImpl implements CommentRepository {

    private final Connection connection = DataSource.getInstance().getConnection();


    public Comment create(Comment comment) {
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement("""
                                            INSERT INTO comments (title, description, timestamp, users_id)
                                                               VALUES (?, ?, CURRENT_TIMESTAMP, ?)
                                                      """);

            preparedStatement.setString(1, comment.getTitle());
            preparedStatement.setString(2,comment.getDescription());
            preparedStatement.setInt(3,comment.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return comment;
    }


    public boolean delete(Comment comment) {

        if(comment == null) {
            return false;
        }

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("""
        DELETE FROM comments WHERE title = ? and users_id = ?
        """);
            preparedStatement.setString(1, comment.getTitle());
            preparedStatement.setInt(2, comment.getUserId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



    public void edit(Comment comment) {
        delete(comment);
        create(comment);
    }


    private Comment fromResultSet(ResultSet resultSet) {
        Comment comment = new Comment();
        try {
            comment.setTitle(resultSet.getString(2));
            comment.setDescription(resultSet.getString(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comment;
    }

    @Override
    public List<Comment> getAll(Integer id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Comment> list = null;

        try {
            preparedStatement = connection.prepareStatement("""
                    SELECT * FROM comments WHERE users_id = ?
        """);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
         list = new ArrayList<>();
        while(resultSet.next()) {
            Comment comment = fromResultSet(resultSet);
            list.add(comment);
        }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
