package com.springmvc.dao;

import com.springmvc.entity.User;
import com.springmvc.entity.UserLoginAudit;
import com.springmvc.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserLoginAuditDAOImpl implements UserLoginAuditDAO {

    private final SessionFactory sessionFactory;

    public UserLoginAuditDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public String save(UserModel user, String userRole, long loginTime) {
        Session session = null;
        Transaction tx = null;
        String res = "Login data successfully saved";
        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            User userEntity = (User) session.createQuery ("from User U where U.userId=:userId").setInteger ("userId", user.getUserId ()).uniqueResult ();
            UserLoginAudit userLoginAudit = new UserLoginAudit(userEntity.getUsername(), userRole, loginTime, userEntity);
            session.save(userLoginAudit);
            tx.commit ();
        }
        catch (Exception e) {
            System.err.println (e.getMessage ());
            rollBack(tx);
            res ="UserAuditLogin data failed to save";

        }
        finally{
            closeSession (session);
        }
        return res;
    }

    @Override
    public UserLoginAudit findByUserIdAndUserName (long id, String userName) {
        return null;
    }


    @Override
    public void rollBack (Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback ();
            } catch (Exception e1) {
                System.err.println (e1.getMessage ());
            }
        }
    }

    @Override
    public void closeSession (Session session) {
        if (session != null) {
            try {
                session.close ();
            } catch (Exception e) {
                System.err.println (e.getMessage ());
            }

        }
    }


}

