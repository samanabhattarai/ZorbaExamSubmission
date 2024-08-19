package com.customerrproductpexam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "customer_id", length = 6)
        private String custId;

        @Column(name = "customer_name", length=100)
        private String custName;

        @Column(name = "customer_email", length=100)
        private String custEmail;

        @Column(name = "customer_mobile", length=11)
        private long custMobile;

        @Column(name = "final_product_price", length=20)
        private double finalProductPrice;
    }
