package com.springmvc.dao;

import com.springmvc.entity.Role;
import com.springmvc.model.RoleModel;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public interface RoleDao{
    RoleModel getRoleByName (String roleName);
    Role saveRole(Role role);
    void closeSession(Session session);
    void rollBack (Transaction tx);
}

