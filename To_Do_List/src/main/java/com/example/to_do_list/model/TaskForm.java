package com.example.to_do_list.model;

import jakarta.validation.constraints.NotBlank; import jakarta.validation.constraints.Size;

public class TaskForm {
    @NotBlank(message = "Заглавието е задължително!")
    @Size(max = 100, message = "Заглавието не може да бъде по-дълго от 100 символа!")
    private String title;

    private String description;

    public TaskForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}