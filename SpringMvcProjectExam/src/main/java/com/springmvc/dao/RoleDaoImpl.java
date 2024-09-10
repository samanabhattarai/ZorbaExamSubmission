package com.springmvc.dao;

import com.springmvc.entity.Role;

import com.springmvc.entity.User;
import com.springmvc.model.RoleModel;
import com.springmvc.model.UserModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    public RoleDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role saveRole(Role role) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(role);
            tx.commit ();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            rollBack(tx);
        } finally {
            closeSession (session);
        }

        return role;

    }


    @Override
    public RoleModel getRoleByName(String roleName) {
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM Role r where r.roleName = :roleName");
            query.setString("roleName", roleName);
            Role role = (Role) query.uniqueResult();
            if( role != null ) {
                roleModel.setRoleName(role.getRoleName());
                roleModel.setRoleId (role.getRoleId ());
            }
        } catch (Exception e) {
            System.err.println (e.getMessage ());
        }
        finally {
            closeSession (session);
        }
        return roleModel;

    } RoleModel roleModel = new RoleModel ();
        Session session = null;

        @Override
    public void rollBack (Transaction tx) {
        if(tx != null ) {
            try {
                tx.rollback ();
            } catch (Exception e1) {
                System.err.println (e1.getMessage ());
            }
        }
    }

    @Override
    public void closeSession (Session session) {
        if(session != null ){
            try{
                session.close();
            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }


}
