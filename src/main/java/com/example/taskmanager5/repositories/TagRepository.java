package com.example.taskmanager5.repositories;

import com.example.taskmanager5.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
    @Query("SELECT t FROM Tag t WHERE t.name LIKE %:name%")
    List<Tag> searchByName(@Param("name") String name);

    @Query("SELECT DISTINCT t FROM Tag t JOIN t.tasks ta WHERE ta.id = :taskId")
    List<Tag> findTagsByTaskId(@Param("taskId") Long taskId);
}
