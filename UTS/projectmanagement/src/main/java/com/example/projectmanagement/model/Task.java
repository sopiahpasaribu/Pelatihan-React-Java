package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String status; // e.g., "Not Started", "In Progress", "Completed"
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}