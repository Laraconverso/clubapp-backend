package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.model.Employee;

import java.util.List;

public interface EmployeeService {

        //List<EmployeeDTO> listAllEmployees();
        List<Employee> listAllEmployees();
        EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

        Employee getEmployeeById(Long id);

        Employee updateEmployee(EmployeeDTO employeeDTO);

        void deleteEmployee(Long id);

        EmployeeDTO getRole(Long id);

        Employee getEmployeeByDni(String dni);

}





