package com.example.task2.controllers;

import com.example.task2.models.Employee;
import com.example.task2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}/fullname")
    public String getAllFullName(@PathVariable Long id) {
        return employeeService.getFullName(id);
    }

    @GetMapping("/{id}/position")
    public String getPosition(@PathVariable Long id) {
        return employeeService.getPosition(id);
    }

    @GetMapping("/{id}/department")
    public String getDepartmentName(@PathVariable Long id) {
        return employeeService.getDepartmentName(id);
    }
}



