package com.example.demo.security.oauth2security.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;

    @Column(nullable = false)
    private String addressLine;

}
