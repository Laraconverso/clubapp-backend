package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.EmployeeRepository;
import com.APIclubApp.clubApp.security.usersecurity.model.RoleEntity;
import com.APIclubApp.clubApp.security.usersecurity.repository.RoleSecurityRepository;
import com.APIclubApp.clubApp.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private RoleSecurityRepository roleSecurityRepository;
    @Autowired
    //ObjectMapper objectMapper;
    public ModelMapper modelMapper; // Necesitamos una instancia de ModelMapper para convertir entre Employee y EmployeeDTO

    @Override
    public List<Employee> listAllEmployees() {

        return employeeRepository.findAll();

    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws AlreadyExistsException, NotFoundException {
        Employee getEmployeeByUserDni = employeeRepository.getEmployeeByUserDni(employeeDTO.getUserDni());
        if (getEmployeeByUserDni != null) {
            throw new AlreadyExistsException("Employee already exists with DNI: " + employeeDTO.getUserDni());
        }

        Employee getEmployeeByUserEmail = employeeRepository.getEmployeeByUserEmail(employeeDTO.getUserEmail());
        if (getEmployeeByUserEmail != null) {
            throw new AlreadyExistsException("Employee already exists with email: " + employeeDTO.getUserEmail());
        }

        RoleEntity roleEntity = roleSecurityRepository.findByRolename("Employee")
                .orElseThrow(() -> new NotFoundException("Role not found"));

        Club club = clubRepository.findById(employeeDTO.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found"));

        Employee newEmployee = modelMapper.map(employeeDTO, Employee.class);

        newEmployee.setRole(roleEntity);
        newEmployee.setClub(club);

        Employee savedEmployee = employeeRepository.save(newEmployee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with ID: " + id));        /*Employee employee = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employee, EmployeeDTO.class);*/
    }

    @Override
    public Employee updateEmployee(EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeDTO.getEmployeeNumber())
                .orElseThrow(() -> new NotFoundException("Employee not found with ID: " + employeeDTO.getEmployeeNumber()));

        Club club = clubRepository.findById(employeeDTO.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found with ID: " + employeeDTO.getClubId()));

//        Employee existingEmployeeWithSameDNI = employeeRepository.getEmployeeByUserDni(employeeDTO.getUserDni());
//        if (existingEmployeeWithSameDNI!=null
//                && !existingEmployeeWithSameDNI.getUserDni().equals(existingEmployee.getUserDni())) {
//            throw new AlreadyExistsException("Employee already exists with dni: " + employeeDTO.getUserDni());
//        }
        Employee existingEmployeeWithSameEmail = employeeRepository.getEmployeeByUserEmail(employeeDTO.getUserEmail());
        if (existingEmployeeWithSameEmail!=null
                && !existingEmployeeWithSameEmail.getUserEmail().equals(existingEmployee.getUserEmail())) {
            throw new AlreadyExistsException("Employee already exists with email: " + employeeDTO.getUserEmail());
        }

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setClub(club);

        employee = employeeRepository.save(employee);

        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Employee not found with ID: " + id);
        }    }

    @Override
    public EmployeeDTO getRole(Long id) {
       // return employeeRepository.getReferenceById(id);
        Employee employee = employeeRepository.getReferenceById(id);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public Employee getEmployeeByDni(String dni){
        Employee employee = employeeRepository.getEmployeeByUserDni(dni);
        if (employee == null) {
            throw new NotFoundException("Employee not found with DNI: " + dni);
        }
        return employee;
        /*Employee employee = employeeRepository.getEmployeeByUserDni(dni);
        return modelMapper.map(employee, EmployeeDTO.class);*/
    }
}
