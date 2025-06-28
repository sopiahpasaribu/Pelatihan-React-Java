package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        // Pastikan employee dan department ada
        if (project.getEmployee() != null && project.getEmployee().getId() != null) {
            project.setEmployee(employeeRepository.findById(project.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found")));
        }
        
        if (project.getDepartment() != null && project.getDepartment().getId() != null) {
            project.setDepartment(departmentRepository.findById(project.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found")));
        }
        
        return projectRepository.save(project);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Project not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Project not found with id: " + id));
        
        project.setTitle(projectDetails.getTitle());
        
        if (projectDetails.getEmployee() != null && projectDetails.getEmployee().getId() != null) {
            project.setEmployee(employeeRepository.findById(projectDetails.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found")));
        }
        
        if (projectDetails.getDepartment() != null && projectDetails.getDepartment().getId() != null) {
            project.setDepartment(departmentRepository.findById(projectDetails.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found")));
        }
        
        return projectRepository.save(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }
}
