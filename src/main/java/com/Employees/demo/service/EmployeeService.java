package com.Employees.demo.service;

import com.Employees.demo.entity.Department;
import com.Employees.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(  String empName, String designation, int deptId);
    List<Employee> deleteEmployeeById(int id);

    Employee getEmployeeDetails(int empId);

    boolean isEmployeePresent(int empId);
    List<Employee> getEmployeesByDesignation(String designation);

    List<Employee> getEmployeesByDepartmentId(int dept_id);

    Department createDepartment(String deptName);
    List<Department> deleteDepartmentById(int id);

    Employee addifNotPresent(int id);
}
