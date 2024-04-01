package com.example.task2.services;

import com.example.task2.models.Department;
import com.example.task2.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
