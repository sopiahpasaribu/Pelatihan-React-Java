package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    
    @OneToMany(mappedBy = "department")
    private List<Project> projects;
}