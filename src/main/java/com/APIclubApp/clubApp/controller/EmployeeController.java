package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Crear un empleado")
    @PostMapping("/save")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String passWEncrypt= passwordEncoder.encode(employeeDTO.getUserPassword());
            employeeDTO.setUserPassword(passWEncrypt);
            return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todos los empleados")
    @GetMapping("/list")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Employee>> listAllEmployee(){
        List<Employee> employees= employeeService.listAllEmployees();
        if (employees.isEmpty()){
            System.out.println("Aún no hay empleados en la base de datos.");
        }
        return ResponseEntity.ok(employees);
    }
    /*public ResponseEntity<List<EmployeeDTO>> listAllEmployee(){
        List<EmployeeDTO> employeeDTOs = employeeService.listAllEmployees();
        return ResponseEntity.ok(employeeDTOs);
        return ResponseEntity.ok(employeeService.listAllEmployees());
    }*/


    @Operation(summary = "Obtener un empleado por su ID")
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
            Employee employee= employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee) ;
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener un empleado por su DNI")
    @GetMapping("/getByDni/{dni}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getEmployeeByDni(@PathVariable String dni){
        try {
            Employee employee = employeeService.getEmployeeByDni(dni);
            return ResponseEntity.ok(employee);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un empleado")
    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            Employee updatedEmployee = employeeService.updateEmployee(employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un empleado por su ID")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().body("Employee deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}