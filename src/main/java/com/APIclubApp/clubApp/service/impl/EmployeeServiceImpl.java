package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.EmployeeRepository;
import com.APIclubApp.clubApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {return employeeRepository.findById(id).get();}

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getRole(Long id) {
        return employeeRepository.getReferenceById(id);
    }

    @Override
    public Employee getEmployeeByDni(String dni){return employeeRepository.getEmployeeByUserDni(dni);}
}
