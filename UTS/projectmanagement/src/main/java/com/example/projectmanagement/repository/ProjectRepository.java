package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByTitleContainingIgnoreCase(String keyword);
    
    @Query("SELECT p FROM Project p ORDER BY p.title ASC")
    List<Project> findAllOrderByTitleAsc();
    
    @Query("SELECT p FROM Project p ORDER BY p.title DESC")
    List<Project> findAllOrderByTitleDesc();
}