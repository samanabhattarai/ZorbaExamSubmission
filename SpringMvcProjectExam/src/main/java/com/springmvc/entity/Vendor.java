package com.springmvc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int vendorId;
    private String vendorName;
    private String password;
    private String role;

    public Vendor ( String vendorName, String password, String role) {
        this.vendorName = vendorName;
        this.password = password;
        this.role = role;
    }

    public Vendor () {

    }
}
