package com.springmvc.service;

import com.springmvc.dao.InventoryDao;
import com.springmvc.model.InventoryModel;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryDao inventoryDao;

    public InventoryService (InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public String saveInventory (InventoryModel inventoryModel) {
        return  inventoryDao.saveInventory (inventoryModel);
    }
}