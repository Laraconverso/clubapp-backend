package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.service.EmployeeService;
import jakarta.annotation.security.PermitAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper; // Necesitamos una instancia de ModelMapper para convertir entre EmployeeDTO y Employee

    /*@PostMapping("/save")
    @PermitAll
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        String passWEncrypt= passwordEncoder.encode(employee.getUserPassword());
        employee.setUserPassword(passWEncrypt);
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Employee>> listAllEmployee(){
        return ResponseEntity.ok(employeeService.listAllEmployees());
    }

    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        ResponseEntity<Employee> response;

        if (employeeService.getEmployeeById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(employeeService.getEmployeeById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping("/getByDni/{dni}")
    @PermitAll
    public ResponseEntity<Employee> getEmployeeByDni(@PathVariable String dni){
        ResponseEntity<Employee> response;

        if (employeeService.getEmployeeByDni(String.valueOf(dni))!=null){
            response = ResponseEntity.ok(employeeService.getEmployeeByDni(String.valueOf(dni))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Employee> updateUser(@RequestBody Employee employee){
        ResponseEntity<Employee> response;
        if (employee.getEmployeeNumber() != null && employeeService.getEmployeeById(employee.getEmployeeNumber()) != null){
            response = ResponseEntity.ok(employeeService.saveEmployee(employee));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        employeeService.deleteEmployee(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted");
    }*/

    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        String passWEncrypt= passwordEncoder.encode(employeeDTO.getUserPassword());
        employeeDTO.setUserPassword(passWEncrypt);
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Employee>> listAllEmployee(){

        return ResponseEntity.ok(employeeService.listAllEmployees());
    }
    /*public ResponseEntity<List<EmployeeDTO>> listAllEmployee(){
        List<EmployeeDTO> employeeDTOs = employeeService.listAllEmployees();
        return ResponseEntity.ok(employeeDTOs);
        return ResponseEntity.ok(employeeService.listAllEmployees());
    }*/



    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        ResponseEntity<Employee> response;

        if (employeeService.getEmployeeById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(employeeService.getEmployeeById(Long.valueOf(id))) ;
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/getByDni/{dni}")
    @PermitAll
    public ResponseEntity<Employee> getEmployeeByDni(@PathVariable String dni){
        Employee employee = employeeService.getEmployeeByDni(dni);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<EmployeeDTO> updateUser(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(employeeDTO);
        if (updatedEmployeeDTO != null) {
            return ResponseEntity.ok(updatedEmployeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
