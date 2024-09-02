package com.springmvc.entity;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "userInfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Integer userId;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="mobile")
    private Integer mobile;
    @Column(name="username")
    private String userName;
    @Column(name="password")
    private String password;
}
