package com.example.task2.services;

import com.example.task2.models.Employee;
import com.example.task2.projections.EmployeeProjection;
import com.example.task2.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No employee with this id"));
    }


    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        if (!employeeRepository.existsById(id)) throw new IllegalArgumentException("No employee with this id");
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) throw new IllegalArgumentException("No employee with this id");
        employeeRepository.deleteById(id);
    }

    public String getFullName(Long id) {
        EmployeeProjection projection = employeeRepository.findProjectionById(id).orElseThrow(()->new IllegalArgumentException("No employee with this id exists"));;
        return projection.getFullName();
    }

    public String getPosition(Long id) {
        EmployeeProjection projection = employeeRepository.findProjectionById(id).orElseThrow(()->new IllegalArgumentException("No employee with this id exists"));
        return projection.getPosition();
    }

    public String getDepartmentName(Long id) {
        EmployeeProjection projection = employeeRepository.findProjectionById(id).orElseThrow(()->new IllegalArgumentException("No employee with this id exists"));
        return projection.getDepartmentName();
    }
}
