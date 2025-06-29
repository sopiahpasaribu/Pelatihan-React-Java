package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/by-id/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/update/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setDepartment(projectDetails.getDepartment());
            return projectRepository.save(project);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    @GetMapping("/search/{keyword}")
    public List<Project> searchProjects(@PathVariable String keyword) {
        return projectRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @GetMapping("/sort/asc")
    public List<Project> sortProjectsAsc() {
        return projectRepository.findAllOrderByTitleAsc();
    }

    @GetMapping("/sort/desc")
    public List<Project> sortProjectsDesc() {
        return projectRepository.findAllOrderByTitleDesc();
    }
}