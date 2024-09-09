package com.springmvc.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user_info")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    private String category;
    private String name;
    private int quantity;
    private double price;
    private String image;
    private String description;
}
