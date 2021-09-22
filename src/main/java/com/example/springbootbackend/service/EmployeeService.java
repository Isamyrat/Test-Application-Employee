package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.model.Project;
import com.example.springbootbackend.repository.EmployeeRepository;
import com.example.springbootbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonalInformationService personalInformationService;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> addEmployee(String firstName, String lastName) {
        Employee employees = new Employee(firstName,lastName);
        employeeRepository.save(employees);
        return employeeRepository.findAll();
    }

    public Employee addEmployeeProject(Long idEmployee, Long idProject) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + idEmployee));

        Project project = projectRepository.findById(idProject)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:" + idProject));

        List<Project> projects = employee.getProjects();

        projects.add(project);
        employee.setProjects(projects);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee findEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return employee;
    }


    public Employee updateEmployee(Long id, String firstName, String lastName ){
        Employee employee  = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));


        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        Employee updateEmployee = employeeRepository.save(employee);
        return updateEmployee;
    }

    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));

        personalInformationService.deletePersonalInformation(employee.getId());

        addressService.deleteAddress(employee.getId());
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Boolean>> deleteEmployeeProject(Long idEmployee, Long idProject) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + idEmployee));

        List<Project> projects = employee.getProjects();
        System.out.println(idEmployee + " " + idProject);
        projects.removeIf(e -> e.getId() == idProject);
        employee.setProjects(projects);
        employeeRepository.save(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
