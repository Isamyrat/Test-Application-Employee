package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/employees")
    public List<Employee> addUser(@RequestBody String firstName, String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }
    @PostMapping("/employees/project")
    public Employee addEmployeeProject(@RequestBody Long idEmployee, Long idProject) {
        return employeeService.addEmployeeProject(idEmployee, idProject);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/employees/project/{id}")
    public Employee getEmployeeProjectById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody String firstName, String lastName) {
        return employeeService.updateEmployee(id, firstName, lastName);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws InterruptedException {

        return employeeService.deleteEmployee(id);
    }
    @DeleteMapping("/employees/project/{idEmployee}/{idProject}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeProject(@PathVariable Long idEmployee, @PathVariable Long idProject) throws InterruptedException {

        return employeeService.deleteEmployeeProject(idEmployee,idProject);
    }

}
