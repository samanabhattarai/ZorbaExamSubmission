package com.springmvc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "inventory_category")
public class InventoryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "inventoryCategory")
    private Set<Inventory> inventories = new HashSet<>();

    public InventoryCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public InventoryCategory () {
    }
}
