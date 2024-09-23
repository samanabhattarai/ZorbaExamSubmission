package com.springmvc.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_login_audit")
public class UserLoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userType;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
}
