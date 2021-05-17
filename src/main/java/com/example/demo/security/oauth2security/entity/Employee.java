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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Integer id;

    @Column(nullable = false)
    @JsonProperty(required = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

    @Column(name = "salary")
    @JsonProperty
    private Integer salary;

    @Column(nullable = false)
    @JsonProperty(required = true)
    private Boolean isActive;



    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "addressId"))
    @JsonProperty(required = true)
    private List<Address> addressList;


}
