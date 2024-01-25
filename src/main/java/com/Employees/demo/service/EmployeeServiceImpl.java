package com.Employees.demo.service;

import com.Employees.demo.entity.Department;
import com.Employees.demo.entity.Employee;
import com.Employees.demo.repository.DepartmentRepository;
import com.Employees.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;


    @Override
    public Employee createEmployee(String empName, String designation, int deptId) {
        if(empName==null || empName.isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        Optional<Department> ds1= departmentRepository.findById(deptId);
        Department d1 = ds1.get();
        Employee e1 = new Employee();
        e1.setEmpName(empName);
        e1.setDesignation(designation);
    e1.setDepartment(d1);
        return employeeRepository.save(e1);

    }

    @Override
    public List<Employee> deleteEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            throw new RuntimeException("wrong id");
        }
        Employee e1 = employeeOptional.get();
        employeeRepository.delete(e1);
        return employeeRepository.findAll();


    }

    @Override
    public Employee getEmployeeDetails(int empId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);
        if(employeeOptional.isEmpty()){
            throw new RuntimeException("wrong id");
        }
        Employee e1 = employeeOptional.get();
        return e1;
    }


    @Override
    public boolean isEmployeePresent(int empId) {
        return employeeRepository.existsById(empId);

    }

    @Override
    public List<Employee> getEmployeesByDesignation(String designation) {
        return employeeRepository.findByDesignation(designation);
    }




    @Override
    public List<Employee> getEmployeesByDepartmentId(int dept_id) {
        return employeeRepository.findAllByDepartmentId(dept_id);
        }

    @Override
    public Department createDepartment(String deptName) {
        if(deptName==null || deptName.isEmpty()){
            throw new IllegalArgumentException("Name is required");
        }
        Department department = new Department();
        department.setDeptName(deptName);
        return departmentRepository.save(department);


    }

    @Override
    public List<Department> deleteDepartmentById(int id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isEmpty()) {
            throw new RuntimeException("Department not found with id: " + id);
        }

        Department department = departmentOptional.get();

        List<Employee> employees = employeeRepository.findByDepartment(department);

        for (Employee employee : employees) {
//           either update or delete
            // employee.setDepartment(null);
            // employeeRepository.save(employee);
            employeeRepository.delete(employee);
        }

        departmentRepository.delete(department);

        return departmentRepository.findAll();
    }

    @Override
    public Employee addifNotPresent(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            return null;
        }
        else{
            String EmpName = "Name1";
            String EmpDesig = "Desig1";

            Employee newEmployee = createEmployee(EmpName, EmpDesig, id);

            return newEmployee;        }
    }


}
