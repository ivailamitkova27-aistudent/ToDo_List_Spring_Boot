package com.example.to_do_list.service;

import com.example.to_do_list.model.Task; import org.springframework.stereotype.Service;

import java.util.List;

@Service public class StatsService {
    private final TaskService taskService;

    public StatsService(TaskService taskService) {
        this.taskService = taskService;
    }

    // Общ брой задачи
    public int getTotalTasks() {
        return taskService.getAllTasks().size();
    }

    // Брой завършени задачи
    public long getCompletedTasks() {
        return taskService.getAllTasks()
                .stream()
                .filter(Task::isCompleted)
                .count();
    }

    // Брой незавършени задачи
    public long getPendingTasks() {
        return taskService.getAllTasks()
                .stream()
                .filter(task -> !task.isCompleted())
                .count();
    }

}
