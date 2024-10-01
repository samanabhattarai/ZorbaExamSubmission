package com.springmvc.dao;

import com.springmvc.entity.UserLoginAudit;
import com.springmvc.model.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface UserLoginAuditDAO {
    String save(UserModel user, String userRole, long loginTime);
    UserLoginAudit findByUserIdAndUserName(long id, String userName);
    void rollBack(Transaction tx);
    void closeSession(Session session);

}
