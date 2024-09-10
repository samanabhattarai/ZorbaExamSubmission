package com.springmvc.dao;

import com.springmvc.entity.Inventory;
import com.springmvc.entity.InventoryCategory;
import com.springmvc.model.InventoryModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class InventoryDaoImpl implements InventoryDao {

    private final SessionFactory sessionFactory;

    public InventoryDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String saveInventory (InventoryModel inventoryModel) {
        String res = "Inventory data successfully saved";
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            Inventory inventory = getInventory(inventoryModel);
            session.persist (inventory.getInventoryCategory ());
            session.persist (inventory);
            tx.commit ();
        } catch (Exception e) {
            System.err.println (e.getMessage ());
            rollBack (tx);
            res = "Inventory data failed to save";
        } finally {
            closeSession (session);
        }
        return res;
    }

    private static Inventory getInventory (InventoryModel inventoryModel) {
        InventoryCategory category = new InventoryCategory (inventoryModel.getCategory ());

        Inventory inventory = new Inventory (
                inventoryModel.getName (),
                inventoryModel.getQuantity (),
                inventoryModel.getPrice (),
                inventoryModel.getImage (),
                inventoryModel.getDescription (), category);
        Set<Inventory> inventories = new HashSet<> ();
        inventories.add (inventory);
        category.setInventories (inventories);
        return inventory;
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
