package com.example.taskmanager5.controllers;

import com.example.taskmanager5.models.Comment;
import com.example.taskmanager5.models.Task;
import com.example.taskmanager5.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/task/{taskId}")
    public List<Comment> getCommentsByTask(@PathVariable Long taskId) {
        Task task = new Task();
        task.setId(taskId);
        return commentService.getCommentsByTask(task);
    }

    @GetMapping("/author/{authorId}")
    public List<Comment> getCommentsByAuthorId(@PathVariable Long authorId) {
        return commentService.getCommentsByAuthorId(authorId);
    }

    @GetMapping("/recent/task/{taskId}")
    public List<Comment> getRecentCommentsByTaskId(@PathVariable Long taskId) {
        return commentService.getRecentCommentsByTaskId(taskId);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        Comment updatedComment = commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}