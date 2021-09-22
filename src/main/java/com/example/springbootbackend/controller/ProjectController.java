package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Project;
import com.example.springbootbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/project")
    public List<Project> getAll(){
        return projectService.getAll();
    }

    @PostMapping("/project")
    public List<Project> addProject(@RequestBody String name, Date startDate, Date endDate, float price) {
        return projectService.addProject(name,startDate,endDate,price);
    }

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.findProjectById(id);
    }

    @PutMapping("/project/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody String name, Date startDate, Date endDate, float price) {
        return projectService.updateProject(id, name, startDate,endDate,price);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable Long id) {

        return projectService.deleteProject(id);
    }

}
