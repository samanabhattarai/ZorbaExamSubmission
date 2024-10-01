package com.springmvc.dao;

import com.springmvc.entity.ExecutionAuditCheck;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface ExecutionAuditCheckDao {

    String saveExecutionAuditCheck(ExecutionAuditCheck executionAuditCheck);
    void rollBack(Transaction tx);
    void closeSession(Session session);
}
