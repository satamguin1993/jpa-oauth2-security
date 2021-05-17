package com.example.demo.security.oauth2security.repository;

import com.example.demo.security.oauth2security.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
