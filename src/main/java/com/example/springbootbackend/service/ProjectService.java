package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Project;
import com.example.springbootbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll(){

        return projectRepository.findAll();
    }

    public List<Project> addProject(String name, Date startDate, Date endDate, float price) {
        Project project = new Project(name,startDate,endDate,price);
        projectRepository.save(project);
        return projectRepository.findAll();
    }

    public Project findProjectById(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return project;
    }

    public Project updateProject(Long id, String name, Date startDate, Date endDate, float price){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));

        project.setName(name);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setPrice(price);

        Project updateProject = projectRepository.save(project);
        return updateProject;
    }

    public ResponseEntity<Map<String, Boolean>> deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        projectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
