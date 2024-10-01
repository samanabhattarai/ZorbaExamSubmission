package com.servletdemo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"appointments"})
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patient_id")
    private Integer patientId;
    @Column(name="patient_name")
    private String patientName;
    @Column(name="mobile")
    private String patientMobile;
    @Column(name="email")
    private String patientEmail;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<> ();
}
