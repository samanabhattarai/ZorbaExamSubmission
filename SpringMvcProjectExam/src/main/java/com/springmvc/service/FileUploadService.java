package com.springmvc.service;

import com.springmvc.dao.InventoryDao;
import com.springmvc.entity.Inventory;
import com.springmvc.entity.InventoryCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FileUploadService {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private ExcelService excelService;

    public void save (MultipartFile file) {
        try {
            Set<InventoryCategory> inventories = excelService.excelToInventory(file.getInputStream());
            log.info ("Inventories: {}", inventories);
            inventoryDao.saveInventories(inventories);
        } catch (IOException e) {
            throw new RuntimeException ("fail to store excel data: " + e.getMessage ());
        }
    }


}


