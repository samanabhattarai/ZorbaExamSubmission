package com.springmvc.model;

import lombok.Data;

@Data
public class InventoryModel {
    private int inventoryId;
    private String category;
    private String name;
    private int quantity;
    private double price;
    private String image;
    private String description;
}
