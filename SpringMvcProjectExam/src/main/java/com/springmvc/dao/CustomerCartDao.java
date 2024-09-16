package com.springmvc.dao;

import com.springmvc.model.CategoryInventoryModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface CustomerCartDao {

    String saveCustomerCart(int userId, int inventoryId);
    void rollBack(Transaction tx);
    void closeSession(Session session);
}
