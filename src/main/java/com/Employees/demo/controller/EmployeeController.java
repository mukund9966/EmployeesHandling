package com.Employees.demo.controller;

import com.Employees.demo.DemoApplication;
import com.Employees.demo.dto.Departmentdto;
import com.Employees.demo.dto.Employeedto;
import com.Employees.demo.entity.Department;
import com.Employees.demo.entity.Employee;
import com.Employees.demo.exception.InvalidEmpNameException;
import com.Employees.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping("/employee/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employeedto employeedto) {
        try {
            if (employeedto.getEmpName() == null || employeedto.getEmpName().isEmpty()) {
                throw new IllegalArgumentException("first name is required");
            }
            Employee employee = employeeService.createEmployee(employeedto.getEmpName(), employeedto.getDesignation(), employeedto.getDeptId() );

            return new ResponseEntity<>(employee, HttpStatus.CREATED);

        }
        catch (InvalidEmpNameException | IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/department/create")
    public ResponseEntity<?> createDepartment(@RequestBody Departmentdto departmentdto) {
        try {
            if (departmentdto.getDeptName() == null || departmentdto.getDeptName().isEmpty()) {
                throw new IllegalArgumentException("department name is required");
            }
            Department department = employeeService.createDepartment(departmentdto.getDeptName());
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
    }

    @GetMapping("/department/{deptId}/employees")
    public ResponseEntity<?> getEmployeesByDepartmentId(@PathVariable Integer deptId){
        return ResponseEntity.ok(employeeService.getEmployeesByDepartmentId(deptId));
    }

    @GetMapping("/employees/{designation}")
    public ResponseEntity<?> getEmployeesByDesignation(@PathVariable String designation){
        return ResponseEntity.ok(employeeService.getEmployeesByDesignation(designation));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable int id){
        return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable int id){
        return ResponseEntity.ok(employeeService.deleteDepartmentById(id));
    }

    @PostMapping("/addIfAbsent/{empId}")
    public ResponseEntity<Employee> addIfAbsent(@PathVariable int empId) {
        Employee result = employeeService.addifNotPresent(empId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}

