package com.example.task2.controllers;

import com.example.task2.models.Department;
import com.example.task2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping
    public Department addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }
}
