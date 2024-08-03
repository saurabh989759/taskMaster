package com.example.taskmanager5.services;

import com.example.taskmanager5.exception.ResourceNotFoundException;
import com.example.taskmanager5.models.Comment;
import com.example.taskmanager5.models.Task;
import com.example.taskmanager5.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByTask(Task task) {
        return commentRepository.findByTask(task);
    }

    public List<Comment> getCommentsByAuthorId(Long authorId) {
        return commentRepository.findByAuthorId(authorId);
    }

    public List<Comment> getRecentCommentsByTaskId(Long taskId) {
        return commentRepository.findRecentCommentsByTaskId(taskId);
    }

    public Comment createComment(Comment comment) {
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentDetails.getContent());
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}