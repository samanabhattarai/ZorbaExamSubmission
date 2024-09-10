package com.springmvc.model;

import lombok.Data;

@Data
public class InventoryModel {
    private Integer inventoryId;
    private String category;
    private String name;
    private Integer quantity;
    private Double price;
    private String image;
    private String description;
}
