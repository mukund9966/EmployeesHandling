package com.Employees.demo.repository;

import com.Employees.demo.entity.Department;
import com.Employees.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByDesignation(String designation);
    List<Employee> findByDepartment(Department department);
    @Query(value = "Select * from employee where dept_id=?1", nativeQuery = true )
    List<Employee> findAllByDepartmentId(int deptId);




}
