package com.springmvc.dao;

import com.springmvc.entity.User;

import java.util.List;

public interface UserDao {
    String saveUser(User user);
    User getUserById(Integer userId);
    List<User> getUsers();
}
