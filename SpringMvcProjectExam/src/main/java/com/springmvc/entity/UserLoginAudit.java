package com.springmvc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = "user")
@Table(name = "user_login_audit")
public class UserLoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="audit_log_id")
    private int auditLogId;

    private String userName;

    private String userType;

    private long loginTime;

    private long logoutTime;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    public UserLoginAudit() {}

    public UserLoginAudit(String userName, String userType, long loginTime,  User user) {
        this.userName = userName;
        this.userType = userType;
        this.loginTime = loginTime;
        this.user = user;
    }
}
