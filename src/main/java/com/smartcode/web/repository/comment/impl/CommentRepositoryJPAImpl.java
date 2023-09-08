package com.smartcode.web.repository.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CommentRepositoryJPAImpl implements CommentRepository {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();



    public Comment create(Comment comment) {
        Session session = sessionFactory.openSession();
        session.save(comment);
        session.close();
        return comment;
    }

    public boolean delete(Comment comment) {
        Session session = sessionFactory.openSession();
        session.delete(comment);
        session.close();
        return true;
    }

    public void edit(Comment comment) {
        Session session = sessionFactory.openSession();
        session.update(comment);
        session.close();
    }

    public List<Comment> getAll(Integer id) {
        Session session = sessionFactory.openSession();
        NativeQuery<Comment> nativeQuery = session.createNativeQuery("SELECT * FROM comment", Comment.class);
        return nativeQuery.list();
    }
}
