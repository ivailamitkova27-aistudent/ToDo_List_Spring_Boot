package com.example.to_do_list.repository;

import com.example.to_do_list.model.Task; import org.springframework.stereotype.Repository;

import java.util.ArrayList; import java.util.List; import java.util.Optional;

@Repository public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    private Long nextId = 1L;

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(nextId++);
            tasks.add(task);
        } else {
            deleteById(task.getId());
            tasks.add(task);
        }

        return task;
    }

    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}