package com.example.demo.security.oauth2security.controller;

import com.example.demo.security.oauth2security.entity.Address;
import com.example.demo.security.oauth2security.entity.Department;
import com.example.demo.security.oauth2security.entity.Employee;
import com.example.demo.security.oauth2security.repository.AddressRepository;
import com.example.demo.security.oauth2security.repository.DepartmentRepository;
import com.example.demo.security.oauth2security.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @PostMapping(path = "/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee,
                         @RequestParam(name = "departmentId", required = false) Integer departmentId) {

        Optional<Department> optional = departmentId != null ? departmentRepository.findById(departmentId) :
                Optional.empty();

        if (optional.isPresent()) {
            employee.setDepartment(optional.get());
        }
        employee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping(path = "/employee")
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(name = "salary", required = true) Integer salary) {

        List<Employee> employeeList = employeeRepository.findEmployeesBySalary(salary);

        if (employeeList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
    }

    @PatchMapping(path = "/employee/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
                       @PathVariable("employeeId") Integer employeeId,
                       @RequestParam(name = "departmentId", required = false) Integer departmentId) {

        Optional<Department> optional = departmentId != null ? departmentRepository.findById(departmentId) :
                Optional.empty();

        if (optional.isPresent()) {
            Department department = optional.get();
            if (employee.getDepartment() != null && employee.getDepartment().getDepartmentName() != null) {
                department.setDepartmentName(employee.getDepartment().getDepartmentName());
                employee.setDepartment(department);
            }
        }
        employee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping(path = "/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if (optional.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(optional.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Employee());
    }

    @DeleteMapping(path = "/employee/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        employeeRepository.deleteById(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/employee/{employeeId}/address/{addressId}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("employeeId") Integer employeeId,
            @PathVariable("addressId") Integer addressId) {

        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            Optional<Address> optionalAddress = addressRepository.findById(addressId);
            if (optional.isPresent()) {
                employee.getAddressList().remove(optionalAddress.get());
            }
            employeeRepository.save(employee);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(path = "/employee/{employeeId}/department/{departmentId}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable("employeeId") Integer employeeId,
                                                  @PathVariable("departmentId") Integer departmentId) {

        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
            if (optionalDepartment.isPresent()) {
                employee.setDepartment(null);
            }
            employeeRepository.save(employee);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
