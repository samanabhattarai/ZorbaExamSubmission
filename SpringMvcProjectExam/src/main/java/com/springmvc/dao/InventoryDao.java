package com.springmvc.dao;

import com.springmvc.model.InventoryModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface InventoryDao {
    String saveInventory(InventoryModel inventoryModel);
    void rollBack(Transaction tx);
    void closeSession(Session session);
}
