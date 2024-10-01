package com.springmvc.service;

import com.springmvc.dao.CustomerCartDao;
import com.springmvc.dao.InventoryDao;
import com.springmvc.dao.RoleDao;
import com.springmvc.dao.UserDao;
import com.springmvc.entity.Role;
import com.springmvc.entity.User;
import com.springmvc.model.RoleModel;
import com.springmvc.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserDao userDAO;

    @Autowired
    private RoleDao roleDAO;

    @Autowired
    private CustomerCartDao customerCartDao;

    public String saveUserData (UserModel userModel) {
        if (userModel.getName () != null && userModel.getEmail () != null && userModel.getMobile () != null
                && userModel.getUsername () != null && userModel.getPassword () != null) {
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


    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        UserModel user =  userDAO.getUserByUserName (userName);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("Not found: " + userName);
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> "ROLE_"+ role.getRoleName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

}
