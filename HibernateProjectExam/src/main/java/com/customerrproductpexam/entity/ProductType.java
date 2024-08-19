package com.customerrproductpexam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_type")
public class ProductType {
          @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_type_id", length =6)
        private int prodTypeId;

        @Column(name = "product_type", length =50)
        private String productType;

        @Column(name = "rate", length =50)
        private double rate;

    }

