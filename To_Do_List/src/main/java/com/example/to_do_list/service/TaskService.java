package com.example.to_do_list.service;

import com.example.to_do_list.model.Task; import com.example.to_do_list.repository.TaskRepository; import org.springframework.stereotype.Service;

import java.util.List;

@Service public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задачата не е намерена!"));
    }

    public Task addTask(String title, String description) {
        Task task = new Task(null, title, description);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, String title, String description) {
        Task task = getTaskById(id);

        task.setTitle(title);
        task.setDescription(description);

        return taskRepository.save(task);
    }

    public void toggleTask(Long id) {
        Task task = getTaskById(id);

        task.setCompleted(!task.isCompleted());

        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}