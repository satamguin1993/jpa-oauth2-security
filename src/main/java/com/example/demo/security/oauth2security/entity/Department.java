package com.example.demo.security.oauth2security.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "departmentId", nullable = false)
    private Integer departmentId;

    @Column
    private String departmentName;

/*    @OneToMany(targetEntity = Employee.class, mappedBy = "departmentId",
            orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees;*/

}
