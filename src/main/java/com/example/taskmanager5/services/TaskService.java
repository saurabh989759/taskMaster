package com.example.taskmanager5.services;

import com.example.taskmanager5.exception.ResourceNotFoundException;
import com.example.taskmanager5.models.Task;
import com.example.taskmanager5.models.TaskStatus;
import com.example.taskmanager5.models.User;
import com.example.taskmanager5.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setCreatedDate(LocalDateTime.now());
        task.setUpdatedDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        task.setUpdatedDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByAssignee(User assignee) {
        return taskRepository.findByAssignee(assignee);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByAssigneeAndStatus(User assignee, TaskStatus status) {
        return taskRepository.findByAssigneeAndStatus(assignee, status);
    }

    public List<Task> getHighPriorityTasks() {
        return taskRepository.findHighPriorityTasks();
    }
}