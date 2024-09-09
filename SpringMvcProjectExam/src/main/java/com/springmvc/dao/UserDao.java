package com.springmvc.dao;

import com.springmvc.entity.User;
import com.springmvc.model.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface UserDao{
    String saveUser(UserModel user);
    UserModel getUserById(int userId);
    List<UserModel> getUsers();
    String updateUserRoles (int userId, String[] roleNames);
    void rollBack(Transaction tx);
    void closeSession(Session session);
    String removeRolesFromUser (int userId, String[] roleNames);
    UserModel getUserByUserNameAndPassword (String userName, String password);
}
