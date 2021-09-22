package com.example.springbootbackend.controller;


import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Page<Employee>> getAll(int pageNumber, int pageSize) {
        System.out.println("+");
        return new ResponseEntity<>(employeeService.getAll(
                PageRequest.of(
                        pageNumber, pageSize)
        ), HttpStatus.OK);
    }
   /* @GetMapping("/employees")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }*/

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDetails));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {

        return employeeService.deleteEmployee(id);
    }

    @DeleteMapping("/employees/project/{idEmployee}/{idProject}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeProject(@PathVariable Long idEmployee, @PathVariable Long idProject) throws InterruptedException {

        return employeeService.deleteEmployeeProject(idEmployee, idProject);
    }

    @PostMapping("/employees/project")
    public Employee addEmployeeProject(@RequestBody Long idEmployee, Long idProject) {
        return employeeService.addEmployeeProject(idEmployee, idProject);
    }

    @GetMapping("/employees/project/{id}")
    public Employee getEmployeeProjectById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

}
