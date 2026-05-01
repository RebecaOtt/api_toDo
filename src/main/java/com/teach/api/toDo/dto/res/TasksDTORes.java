package com.teach.api.toDo.dto.res;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.model.Task;

public record TasksDTORes(Long id, String title, String description, StatusTask statusTask) {
    public TasksDTORes(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStatus());
    }
}
