package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Crear un empleado")
    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        String passWEncrypt= passwordEncoder.encode(employeeDTO.getUserPassword());
        employeeDTO.setUserPassword(passWEncrypt);
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @Operation(summary = "Listar todos los empleados")
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


    @Operation(summary = "Obtener un empleado por su ID")
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

    @Operation(summary = "Obtener un empleado por su DNI")
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

    @Operation(summary = "Actualizar un empleado")
    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<EmployeeDTO> updateFixture(@RequestBody EmployeeDTO employeeDTO){
        Employee updatedEmployee = employeeService.updateEmployee(employeeDTO);
        if (updatedEmployee != null) {
            EmployeeDTO updatedEmployeeDTO = modelMapper.map(updatedEmployee, EmployeeDTO.class);
            return ResponseEntity.ok(updatedEmployeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un empleado por su ID")
    @DeleteMapping("/delete/{id}")
    @PermitAll
    public ResponseEntity<String> deleteFixture(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body("Deleted");
    }
}