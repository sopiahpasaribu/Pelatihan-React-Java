package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String role;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}