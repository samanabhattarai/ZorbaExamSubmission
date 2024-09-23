package com.springmvc.dao;

import com.springmvc.entity.UserLoginAudit;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface UserLoginAuditDAO {
    void save(UserLoginAudit audit);
    UserLoginAudit findById(Long id);
    void update(UserLoginAudit audit);
    void rollBack(Transaction tx);
    void closeSession(Session session);

}
