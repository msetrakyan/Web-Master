package com.smartcode.web.service.comment.impl;

import com.smartcode.web.model.Comment;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.comment.CommentRepository;
import com.smartcode.web.service.comment.CommentService;
import com.smartcode.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CommentServiceJPAImpl implements CommentService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private CommentRepository commentRepository;

    public CommentServiceJPAImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment get(User user, String title) {
        Session session = sessionFactory.openSession();

        Integer id = user.getId();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM comment WHERE id = :id, and title = :title", Comment.class);

        Comment comment = (Comment)nativeQuery.getSingleResult();

        return comment;
    }

    public Comment create(User user, String title, String description) {

        Session session = sessionFactory.openSession();

        Transaction transaction1 = session.beginTransaction();
        Transaction transaction = transaction1;

        Comment comment = new Comment(null, title, description, user.getId());

        commentRepository.create(comment);

        transaction.commit();

        session.close();

        return comment;
    }


    public boolean delete(User user, String title) {

        Session session = sessionFactory.openSession();

        Transaction transaction1 = session.beginTransaction();
        Transaction transaction = transaction1;

        Integer id = user.getId();

        NativeQuery<Comment> nativeQuery = session.createNativeQuery("SELECT * FROM comment WHERE id = :id and title = :title", Comment.class);

        Comment comment = (Comment) nativeQuery.getSingleResult();

        commentRepository.delete(comment);

        transaction.commit();

        session.close();

        return true;
    }


    public List<Comment> getAll(User user) {

        Session session = sessionFactory.openSession();

        List<Comment> list = commentRepository.getAll(user.getId());

        return list;
    }
}
