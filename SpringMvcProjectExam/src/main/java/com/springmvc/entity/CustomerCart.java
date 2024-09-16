package com.springmvc.entity;

import com.springmvc.model.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"user", "inventory"})
@Entity
@Table(name = "customer_cart")
public class CustomerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @ManyToOne
    @JoinColumn(name="inventory_id", referencedColumnName = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    public CustomerCart(Inventory inventory, User user) {
        this.inventory = inventory;
        this.user = user;
    }

    public CustomerCart() {
    }
}
