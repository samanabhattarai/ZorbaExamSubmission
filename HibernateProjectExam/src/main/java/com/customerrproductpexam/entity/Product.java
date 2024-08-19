package com.customerrproductpexam.entity;



import com.customerrproductpexam.entity.Customer;
import com.customerrproductpexam.entity.ProductType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", length = 6)
    private String productId;
    @Column(name = "product_name", length = 50)
    private String productName;
    @Column(name = "product_quantity", length = 50)
    private double productQuantity;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "customer_Id")
    private Customer customer;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;



}
