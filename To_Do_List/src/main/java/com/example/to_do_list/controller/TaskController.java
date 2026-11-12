package com.example.to_do_list.controller;

import com.example.to_do_list.model.Task; import com.example.to_do_list.model.TaskForm; import com.example.to_do_list.service.StatsService; import com.example.to_do_list.service.TaskService;

import jakarta.validation.Valid; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*;

@Controller public class TaskController {
    private final TaskService taskService;
    private final StatsService statsService;

    public TaskController(TaskService taskService, StatsService statsService) {
        this.taskService = taskService;
        this.statsService = statsService;
    }

    @GetMapping("/")
    public String showTasks(Model model) {

        model.addAttribute("tasks", taskService.getAllTasks());

        model.addAttribute("totalTasks", statsService.getTotalTasks());

        model.addAttribute("completedTasks", statsService.getCompletedTasks());

        model.addAttribute("pendingTasks", statsService.getPendingTasks());

        model.addAttribute("taskForm", new TaskForm());

        return "tasks";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("taskForm", new TaskForm());

        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(
            @Valid @ModelAttribute("taskForm") TaskForm taskForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasks());
            model.addAttribute("totalTasks", statsService.getTotalTasks());
            model.addAttribute("completedTasks", statsService.getCompletedTasks());
            model.addAttribute("pendingTasks", statsService.getPendingTasks());

            return "tasks";
        }

        taskService.addTask(
                taskForm.getTitle().trim(),
                taskForm.getDescription()
        );

        return "redirect:/";
    }

    // Форма за редактиране
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Task task = taskService.getTaskById(id);

        TaskForm taskForm = new TaskForm();
        taskForm.setTitle(task.getTitle());
        taskForm.setDescription(task.getDescription());

        model.addAttribute("taskForm", taskForm);
        model.addAttribute("taskId", id);

        return "tasks";
    }

    // Обработване на редактирането
    @PostMapping("/edit/{id}")
    public String updateTask(
            @PathVariable Long id,
            @Valid @ModelAttribute("taskForm") TaskForm taskForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("taskId", id);
            return "tasks";
        }

        taskService.updateTask(
                id,
                taskForm.getTitle().trim(),
                taskForm.getDescription()
        );

        return "redirect:/";
    }

    // Завършване или повторно активиране
    @PostMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Long id) {

        taskService.toggleTask(id);

        return "redirect:/";
    }

    // Изтриване на задача
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return "redirect:/";
    }

}