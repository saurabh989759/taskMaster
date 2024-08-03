package com.example.taskmanager5.repositories;

import com.example.taskmanager5.models.Comment;
import com.example.taskmanager5.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    @Query("SELECT c FROM Comment c WHERE c.author.id = :authorId")
    List<Comment> findByAuthorId(@Param("authorId") Long authorId);

    @Query("SELECT c FROM Comment c WHERE c.task.id = :taskId ORDER BY c.createdDate DESC")
    List<Comment> findRecentCommentsByTaskId(@Param("taskId") Long taskId);
}