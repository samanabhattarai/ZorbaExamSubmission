package com.springmvc.dao;

import com.springmvc.entity.Inventory;
import com.springmvc.entity.InventoryCategory;
import com.springmvc.model.CategoryModel;
import com.springmvc.model.InventoryModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            InventoryCategory category = getInventoryCategory(inventoryModel);

            if(category.getCategoryId () == 0 )
                session.persist (category);
            else
                session.merge (category);

            session.persist(category.getInventories().iterator ().next ());
            tx.commit ();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack (tx);
            res = "Inventory data failed to save";
        } finally {
            closeSession (session);
        }
        return res;
    }

    @Override
    public String saveInventories (Set<InventoryCategory> inventorycategories) {
        String res = "Inventory data successfully saved";
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(InventoryCategory category : inventorycategories) {
                session.persist(category);
                session.persist(category.getInventories ().iterator ().next ());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollBack(tx);
            res = "Inventory data failed to save";
        } finally {
            closeSession(session);
        }
        return res;
    }


    @Override
    public List<CategoryModel> getAllCategories () {
        Session session = null;
        List<CategoryModel> cats = new ArrayList<> ();
        String categoryQuery = "FROM  InventoryCategory C";
        try {
            session = sessionFactory.openSession ();
            Query query = session.createQuery (categoryQuery);
            List<InventoryCategory> categories = (List<InventoryCategory>) query.list ();
            for (InventoryCategory category : categories) {
                CategoryModel catModel = new CategoryModel (category.getCategoryId (), category.getCategoryName ());
                cats.add (catModel);
            }

        } catch (Exception e) {
           e.printStackTrace ();
        } finally {
            closeSession (session);
        }
        return cats;
    }

    @Override
    public List<InventoryModel> getInventoryByCategory (int categoryId) {
        Session session = null;
        List<InventoryModel> inventories = new ArrayList<> ();
        String inventoryQuery = "FROM  Inventory I where I.inventoryCategory.categoryId = :categoryId";
        try {
            session = sessionFactory.openSession ();
            Query query = session.createQuery (inventoryQuery).setInteger ("categoryId", categoryId);
            List<Inventory> inventoryList = (List<Inventory>) query.list ();
            for (Inventory inventory : inventoryList) {
                InventoryModel inventoryModel = new InventoryModel();
                inventoryModel.setInventoryId(inventory.getInventoryId());
                inventoryModel.setName(inventory.getName());
                inventoryModel.setQuantity(inventory.getQuantity());
                inventoryModel.setPrice(inventory.getPrice());
                inventoryModel.setImage(inventory.getImage());
                inventoryModel.setDescription(inventory.getDescription());
                inventories.add (inventoryModel);
            }

        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            closeSession (session);
        }
        return inventories;
    }

    @Override
    public InventoryCategory getCategoryByName (String categoryName) {
        String categoryQuery = "FROM  InventoryCategory C where C.categoryName = :categoryName";
        Session session = null;
        try {
            session = sessionFactory.openSession ();
            Query query = session.createQuery (categoryQuery).setString ("categoryName", categoryName);
            List<InventoryCategory> categories = (List<InventoryCategory>) query.list ();
            for (InventoryCategory category : categories) {
               if(category.getCategoryName ().equals (categoryName)) {
                   return category;
               }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            closeSession (session);
        }
        return null;
    }

    @Override
    public Inventory getInventoryByName (String inventoryName) {
        Session session = null;
        try {
            session = sessionFactory.openSession ();
            Query query = session.createQuery ("FROM  Inventory I where I.name = :name").setString ("name", inventoryName);
            List<Inventory> inventoryList = (List<Inventory>) query.list ();
            for (Inventory inventory : inventoryList) {
                if(inventory.getName ().equals (inventoryName)) {
                    return inventory;
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            closeSession (session);
        }
        return null;
    }

    public  InventoryCategory getInventoryCategory (InventoryModel inventoryModel) {

        InventoryCategory category = getCategoryByName(inventoryModel.getCategory());
        if (category == null) {
            category = new InventoryCategory (inventoryModel.getCategory ());
        }

        Inventory inventory = new Inventory (
                inventoryModel.getName (),
                inventoryModel.getQuantity (),
                inventoryModel.getPrice (),
                inventoryModel.getImage (),
                inventoryModel.getDescription (), category);
        Set<Inventory> inventories = new HashSet<> ();
        inventories.add (inventory);
        category.setInventories(inventories);
        return category;
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
