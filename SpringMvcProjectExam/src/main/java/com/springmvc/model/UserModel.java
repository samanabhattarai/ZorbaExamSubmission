package com.springmvc.model;

import lombok.Data;

import java.util.List;

@Data
public class UserModel {
    private int userId;
    private String name;
    private String email;
    private String mobile;
    private String userName;
    private String password;
    private List<RoleModel> roles;
}



