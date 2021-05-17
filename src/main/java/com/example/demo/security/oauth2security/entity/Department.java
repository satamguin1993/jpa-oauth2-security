package com.example.demo.security.oauth2security.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id", nullable = false)
    @JsonProperty
    private Integer departmentId;

    @Column(nullable = false, unique = true)
    @JsonProperty(defaultValue = "TO_BE_DECIDED")
    private String departmentName;

/*    @OneToMany(targetEntity = Employee.class, mappedBy = "departmentId",
            orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees;*/

}
