package com.springmvc.dao;

import com.springmvc.entity.CustomerCart;
import com.springmvc.entity.Inventory;
import com.springmvc.entity.InventoryCategory;
import com.springmvc.entity.User;
import com.springmvc.model.CategoryInventoryModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CustomerCartDaoImpl implements  CustomerCartDao{
    private final SessionFactory sessionFactory;

    public CustomerCartDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public String saveCustomerCart (int userId, int inventoryId) {
        String res = "Customer Cart data successfully saved";
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            User user = (User) session.get (User.class, userId);
            Inventory inventory = (Inventory) session.get (Inventory.class, inventoryId);
            CustomerCart customerModel = new CustomerCart (inventory, user);
            Set<CustomerCart> carts = new HashSet<> ();
            carts.add (customerModel);
            user.setCarts (carts);
            inventory.setCarts (carts);
            session.persist (customerModel);
            tx.commit ();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack (tx);
            res = "Customer cart data failed to save";
        } finally {
            closeSession (session);
        }
        return res;
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
