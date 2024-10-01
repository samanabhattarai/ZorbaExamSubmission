package com.springmvc.dao;

import com.springmvc.entity.Inventory;
import com.springmvc.entity.InventoryCategory;
import com.springmvc.model.CategoryModel;
import com.springmvc.model.InventoryModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public interface InventoryDao {
    String saveInventory(InventoryModel inventoryModel);
    void rollBack(Transaction tx);
    void closeSession(Session session);
    String saveInventories(Set<InventoryCategory> inventorycategories);

    List<CategoryModel> getAllCategories ();

    List<InventoryModel> getInventoryByCategory (int categoryId);

    InventoryCategory getCategoryByName (String categoryName);

    List<InventoryModel> getAllInventory ();
}
