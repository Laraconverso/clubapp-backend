package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Role;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.EmployeeRepository;
import com.APIclubApp.clubApp.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    private ClubRepository clubRepository;

    //@Autowired
    //private RoleRepository roleRepository;

    @Autowired
    //ObjectMapper objectMapper;
    public ModelMapper modelMapper; // Necesitamos una instancia de ModelMapper para convertir entre Employee y EmployeeDTO

    @Override
    public List<Employee> listAllEmployees() {

        return employeeRepository.findAll();

    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        /*Employee newEmployee=  objectMapper.convertValue(employee, Employee.class);*/
        //return employeeRepository.save(newEmployee);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
        /*Employee employee = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employee, EmployeeDTO.class);*/
    }

    @Override
    public Employee updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO getRole(Long id) {
       // return employeeRepository.getReferenceById(id);
        Employee employee = employeeRepository.getReferenceById(id);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public Employee getEmployeeByDni(String dni){
        return employeeRepository.getEmployeeByUserDni(dni);
        /*Employee employee = employeeRepository.getEmployeeByUserDni(dni);
        return modelMapper.map(employee, EmployeeDTO.class);*/
    }
}
