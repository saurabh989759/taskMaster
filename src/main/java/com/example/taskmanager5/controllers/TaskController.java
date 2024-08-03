package com.example.taskmanager5.controllers;

import com.example.taskmanager5.models.Task;
import com.example.taskmanager5.models.TaskStatus;
import com.example.taskmanager5.models.User;
import com.example.taskmanager5.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assignee/{assigneeId}")
    public List<Task> getTasksByAssignee(@PathVariable Long assigneeId) {
        User assignee = new User();
        assignee.setId(assigneeId);
        return taskService.getTasksByAssignee(assignee);
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/assignee/{assigneeId}/status/{status}")
    public List<Task> getTasksByAssigneeAndStatus(@PathVariable Long assigneeId, @PathVariable TaskStatus status) {
        User assignee = new User();
        assignee.setId(assigneeId);
        return taskService.getTasksByAssigneeAndStatus(assignee, status);
    }

    @GetMapping("/high-priority")
    public List<Task> getHighPriorityTasks() {
        return taskService.getHighPriorityTasks();
    }
}