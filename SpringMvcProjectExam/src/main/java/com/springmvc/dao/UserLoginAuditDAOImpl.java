package com.springmvc.dao;

import com.springmvc.entity.UserLoginAudit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserLoginAuditDAOImpl implements UserLoginAuditDAO {


    private final SessionFactory sessionFactory;

        UserLoginAuditDAOImpl (SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

    @Override
    public void save(UserLoginAudit audit) {
        Session session = sessionFactory.getCurrentSession();
        session.save(audit);
    }

    @Override
    public UserLoginAudit findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (UserLoginAudit) session.get(UserLoginAudit.class, id);
    }

    @Override
    public void update(UserLoginAudit audit) {
        Session session = sessionFactory.getCurrentSession();
        session.update(audit);
    }

    @Override
    public void rollBack (Transaction tx) {

    }

    @Override
    public void closeSession (Session session) {

    }


}

