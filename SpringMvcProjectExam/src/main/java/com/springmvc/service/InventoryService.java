package com.springmvc.service;

import com.springmvc.dao.InventoryDao;
import com.springmvc.model.CategoryModel;
import com.springmvc.model.InventoryModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventoryService {

    private final InventoryDao inventoryDao;

    public InventoryService (InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public String saveInventory (InventoryModel inventoryModel) {
        return inventoryDao.saveInventory (inventoryModel);
    }

    public List<CategoryModel> getAllCategories () {
        return inventoryDao.getAllCategories ();
    }


    public List<InventoryModel> getInventoryByCategory (int categoryId) {
        return inventoryDao.getInventoryByCategory(categoryId);
    }
}

