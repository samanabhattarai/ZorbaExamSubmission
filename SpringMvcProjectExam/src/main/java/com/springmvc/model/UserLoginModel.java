package com.springmvc.model;

import lombok.Data;

@Data
public class UserLoginModel {
    private String userName;
    private String password;
    private String roleName;
}
