package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Employee;

import java.util.List;

public interface EmployeeService {

        public List<Employee> listAllEmployees();

        public  Employee saveEmployee(Employee employee);

        public Employee getEmployeeById(Long id);

        public  Employee updateEmployee(Employee employee);

        public void deleteEmployee(Long id);

        public  Employee getRole(Long id);

        public Employee getEmployeeByDni(String dni);

}
