package com.example.task2.repositories;

import com.example.task2.models.Employee;
import com.example.task2.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

//    @Query("SELECT CONCAT(e.firstName, ' ', e.lastName) AS fullName FROM Employee e WHERE e.id = :id")
    Optional<EmployeeProjection> findProjectionById(Long id);

//    @Query("SELECT e.position AS position FROM Employee e WHERE e.id=id")
//    Optional<EmployeeProjection> findPositionById(Long id);

//    @Query("SELECT e.department.name AS departmentName FROM Employee e WHERE e.id=id")
//    Optional<EmployeeProjection> findDepartmentNameById(Long id);
}
