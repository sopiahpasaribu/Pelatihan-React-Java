package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Cari task berdasarkan nama 
    List<Task> findByNameContainingIgnoreCase(String keyword);

    // Cari task berdasarkan status
    List<Task> findByStatus(String status);

    // Urutkan berdasarkan nama ASC
    @Query("SELECT t FROM Task t ORDER BY t.name ASC")
    List<Task> findAllOrderByNameAsc();

    // Urutkan berdasarkan nama DESC
    @Query("SELECT t FROM Task t ORDER BY t.name DESC")
    List<Task> findAllOrderByNameDesc();

    // Urutkan berdasarkan status ASC
    @Query("SELECT t FROM Task t ORDER BY t.status ASC")
    List<Task> findAllOrderByStatusAsc();

    // Urutkan berdasarkan status DESC
    @Query("SELECT t FROM Task t ORDER BY t.status DESC")
    List<Task> findAllOrderByStatusDesc();

    // Cari task berdasarkan project ID
    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId")
    List<Task> findByProjectId(@Param("projectId") Long projectId);
}
