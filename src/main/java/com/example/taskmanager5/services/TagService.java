package com.example.taskmanager5.services;

import com.example.taskmanager5.exception.ResourceNotFoundException;
import com.example.taskmanager5.models.Tag;
import com.example.taskmanager5.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(Long id, Tag tagDetails) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
        tag.setName(tagDetails.getName());
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    public List<Tag> searchTags(String keyword) {
        return tagRepository.searchByName(keyword);
    }

    public List<Tag> getTagsByTaskId(Long taskId) {
        return tagRepository.findTagsByTaskId(taskId);
    }
}