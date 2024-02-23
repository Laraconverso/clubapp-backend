package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getEmployeeByUserDni(String dni);
}
