package com.springmvc.dao;

import com.springmvc.entity.ExecutionAuditCheck;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ExecutionAuditCheckDaoImpl implements ExecutionAuditCheckDao {

    private final SessionFactory sessionFactory;

    ExecutionAuditCheckDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String saveExecutionAuditCheck (ExecutionAuditCheck executionAuditCheck) {

        String res = "";
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            session.persist (executionAuditCheck);
            tx.commit ();
        } catch (Exception e) {
            System.err.println (e.getMessage ());
            rollBack (tx);
            res = " ExecutionAuditCheck data failed to save";
        } finally {
            closeSession (session);
        }

        return "ExecutionAuditCheck data successfully saved";
    }

    public void rollBack (Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback ();
            } catch (Exception e1) {
                System.err.println (e1.getMessage ());
            }
        }
    }


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

