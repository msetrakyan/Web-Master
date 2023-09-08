package com.smartcode.web.repository.user.impl;

import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryJPAImpl implements UserRepository {


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();



    public User create(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.update(user);
        session.close();
    }

    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("SELECT * FROM users", User.class);
        List<User> list = nativeQuery.list();
        return list;
    }


    public User getById(Integer id) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where id = :id", User.class);
        return nativeQuery.getSingleResult();
    }

    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.delete(getById(id));
    }

    public List<User> findByName(String string) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where name = :name", User.class);
        return nativeQuery.list();
    }

    public User getByUsername(String username) {

        Session session = sessionFactory.openSession();

        NativeQuery<User> nativeQuery = session.createNativeQuery("select * from users where username = :username ", User.class);
        User user = nativeQuery.getSingleResult();
        return user;
    }

    public boolean changePassword(User user, String oldPass, String newPass) {
        if(user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            Session session = sessionFactory.openSession();
            session.update(user);
            session.close();
            return true;
        } else {
            return false;
        }
    }
}
