package com.example.taskmanager5.repositories;

import com.example.taskmanager5.models.Comment;
import com.example.taskmanager5.models.Task;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.beans.JavaBean;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTask(Task task);

    List<Comment> findByAuthorId(Long authorId);

    @Query("SELECT c FROM Comment c WHERE c.taskId = :taskId ORDER BY c.createdDate DESC")
    List<Comment> findRecentCommentsByTaskId(@Param("taskId") Long taskId);


}
