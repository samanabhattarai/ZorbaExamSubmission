package com.springmvc.service;

import com.springmvc.dao.InventoryDao;
import com.springmvc.entity.Inventory;
import com.springmvc.entity.InventoryCategory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


@Service
public class ExcelService {

    @Autowired
    InventoryDao inventoryDao;

    public  Set<InventoryCategory> excelToInventory (InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook (is);

            Sheet sheet = workbook.getSheet ("Sheet1");
            Iterator<Row> rows = sheet.iterator ();

            Set<InventoryCategory> inventoryCategoryList = new HashSet<>();

            int rowNumber = 0;
            while (rows.hasNext ()) {
                Row currentRow = rows.next ();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator ();

                Inventory inventory = new Inventory ();

                int cellIdx = 0;
                while (cellsInRow.hasNext ()) {
                    Cell currentCell = cellsInRow.next ();

                    switch (cellIdx) {
                        case 0:

                            InventoryCategory category  =  new InventoryCategory(currentCell.getStringCellValue().trim ());
                            // get from list first
                            for(InventoryCategory inventoryCategory : inventoryCategoryList) {
                                if(inventoryCategory.getCategoryName().equals(currentCell.getStringCellValue().trim())) {
                                    category = inventoryCategory;
                                }
                            }

                            inventory.setInventoryCategory(category);
                            category.getInventories ().add(inventory);
                            break;

                        case 1:
                            inventory.setName (currentCell.getStringCellValue().trim ());
                            break;

                        case 2:
                            inventory.setQuantity ((int)currentCell.getNumericCellValue());
                            break;

                        case 3:
                            inventory.setPrice (currentCell.getNumericCellValue ());
                            break;

                        case 4:
                            inventory.setDescription (currentCell.getStringCellValue ().trim ());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                inventoryCategoryList.add(inventory.getInventoryCategory());
            }

            workbook.close ();

            return inventoryCategoryList;
        } catch (IOException e) {
            throw new RuntimeException ("fail to parse Excel file: " + e.getMessage ());
        }
    }
}