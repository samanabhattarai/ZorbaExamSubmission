package com.springmvc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "execution_audit_check")
public class ExecutionAuditCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfController;
    private String nameOfMethod;
    private long executionTime;


}
