package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
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

    @Operation(summary = "Guardar un empleado")
    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        String passWEncrypt= passwordEncoder.encode(employee.getUserPassword());
        employee.setUserPassword(passWEncrypt);
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @Operation(summary = "Listar todos los empleados")
    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Employee>> listAllEmployee(){
        return ResponseEntity.ok(employeeService.listAllEmployees());
    }

    @Operation(summary = "Obtener un empleado por su ID")
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

    @Operation(summary = "Obtener un empleado por su DNI")
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

    @Operation(summary = "Actualizar un empleado")
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

    @Operation(summary = "Eliminar un empleado por su ID")
    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        employeeService.deleteEmployee(Long.valueOf(id));
        return ResponseEntity.ok().body("Deleted");
    }
}
