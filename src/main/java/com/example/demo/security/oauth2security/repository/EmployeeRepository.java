package com.example.demo.security.oauth2security.repository;

import com.example.demo.security.oauth2security.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query("select e from Employee e where e.salary=:salary")
    List<Employee> findEmployeesBySalary(@Param("salary") Integer salary);

}
