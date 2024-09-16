package com.springmvc.service;

import com.springmvc.dao.CustomerCartDao;
import com.springmvc.dao.InventoryDao;
import com.springmvc.dao.RoleDao;
import com.springmvc.dao.UserDao;
import com.springmvc.entity.CustomerCart;
import com.springmvc.entity.Inventory;
import com.springmvc.entity.Role;
import com.springmvc.model.CategoryInventoryModel;
import com.springmvc.model.RoleModel;
import com.springmvc.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDAO;

    private final RoleDao roleDAO;

    public UserService (UserDao userDAO, RoleDao roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }


    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    CustomerCartDao customerCartDao;

    public String saveUserData (UserModel userModel) {
        if (userModel.getName () != null && userModel.getEmail () != null && userModel.getMobile () != null
                && userModel.getUserName () != null && userModel.getPassword () != null) {
            //saved data to database
            return userDAO.saveUser (userModel);
        }
        return "Could Not Process";
    }


    public String updateUserRole (int userId, String[] roleNames) {
        for (String roleName : roleNames) {
            RoleModel role = roleDAO.getRoleByName (roleName);
            if (role.getRoleName () == null) {
                Role savedRole = roleDAO.saveRole (new Role (roleName));
                System.out.println ("Saved role :" + savedRole);
            }
        }
        return userDAO.updateUserRoles (userId, roleNames);
    }

    public String removeRolesFromUser (int userId, String[] roleNames) {
        return userDAO.removeRolesFromUser (userId, roleNames);
    }

    public UserModel getUserById (int userId) {
        return userDAO.getUserById (userId);
    }

    public List<UserModel> getUsers () {
        return userDAO.getUsers ();
    }

    public String saveCart(int userId, int inventoryId) {
        return customerCartDao.saveCustomerCart (userId, inventoryId);
    }
}
