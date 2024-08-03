package com.example.taskmanager5.repositories;

import com.example.taskmanager5.models.Task;
import com.example.taskmanager5.models.TaskStatus;
import com.example.taskmanager5.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee(User assignee);
    List<Task> findByStatus(TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.assignee = :assignee AND t.status = :status")
    List<Task> findByAssigneeAndStatus(@Param("assignee") User assignee, @Param("status") TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.priority = 'HIGH' OR t.priority = 'CRITICAL'")
    List<Task> findHighPriorityTasks();}
