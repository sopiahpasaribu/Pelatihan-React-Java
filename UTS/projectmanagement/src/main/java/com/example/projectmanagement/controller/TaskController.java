package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Task;
import com.example.projectmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")  
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Search tasks by name or status
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status) {

        if (name != null) {
            return ResponseEntity.ok(taskRepository.findByNameContainingIgnoreCase(name));
        } else if (status != null) {
            return ResponseEntity.ok(taskRepository.findByStatus(status));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Sort tasks by name or status
    @GetMapping("/sort")
    public ResponseEntity<List<Task>> sortTasks(
            @RequestParam String by,
            @RequestParam(defaultValue = "asc") String direction) {

        List<Task> tasks;
        switch (by.toLowerCase()) {
            case "name":
                tasks = "desc".equalsIgnoreCase(direction) ?
                        taskRepository.findAllOrderByNameDesc() :
                        taskRepository.findAllOrderByNameAsc();
                break;
            case "status":
                tasks = "desc".equalsIgnoreCase(direction) ?
                        taskRepository.findAllOrderByStatusDesc() :
                        taskRepository.findAllOrderByStatusAsc();
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tasks);
    }

    // Get tasks by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    // Create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task savedTask = taskRepository.save(task);
            return ResponseEntity.ok(savedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update task status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(status);
                    return ResponseEntity.ok(taskRepository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
