package com.springmvc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode(exclude = {"inventoryCategory", "carts"})
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventory_id")
    private int inventoryId;

    @Column(name="inventory_name")
    private String name;

    @Column(name="inventory_quantity")
    private int quantity;

    @Column(name="inventory_price")
    private double price;

    @Column(name="inventory_image")
    private String image;

    @Column(name="inventory_desc")
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "category_id")
    private InventoryCategory inventoryCategory;

    public Inventory() {

    }
    public Inventory(String name, int quantity, double price, String image, String description, InventoryCategory inventoryCategory) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.inventoryCategory = inventoryCategory;
    }

    @OneToMany(mappedBy = "inventory")
    private Set<CustomerCart> carts = new HashSet<> ();

}
