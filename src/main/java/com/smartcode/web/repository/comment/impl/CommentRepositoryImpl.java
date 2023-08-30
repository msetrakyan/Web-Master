package com.smartcode.web.repository.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    private final Connection connection = DataSource.getInstance().getConnection();


    public Comment create(User user, Comment comment) {
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement("""
                                            INSERT INTO comments (title, description, timestamp, users_id)
                                                               VALUES (?, ?, CURRENT_TIMESTAMP, ?)
                                                      """);

            preparedStatement.setString(1, comment.getTitle());
            preparedStatement.setString(2,comment.getDescription());
            preparedStatement.setInt(3,user.getId());
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

    public String read(User user, String title) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String desc = null;

        try {
            preparedStatement = connection.prepareStatement("""
        
                    SELECT * FROM comments WHERE title = ?
        """);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            desc =  resultSet.getString(3);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException exception)  {
                System.out.println(exception.getMessage());
            }
        }
       return desc;
    }

    public boolean delete(User user, String title) {

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("""
        DELETE FROM comments WHERE title = ? and users_id = ?
        """);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, user.getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void edit(User user, Comment comment) {
        delete(user, comment.getTitle());
        create(user, comment);
    }

    public List<Comment> getAll(User user) {

        List<Comment> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(("""
            SELECT * FROM comments WHERE users_id = ?
            """));
            preparedStatement.setInt(1, user.getId());

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Comment comment = fromResultSet(resultSet);
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
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

}
