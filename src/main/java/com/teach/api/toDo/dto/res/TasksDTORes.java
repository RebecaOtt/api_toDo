package com.teach.api.toDo.dto.res;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.model.Task;

public record TasksDTORes(Long id, String title, String description, StatusTask statusTask) {
    public static TasksDTORes ModelToDTO(Task task) {
        return new TasksDTORes(task.getId(), task.getTitle(), task.getDescription(), task.getStatus());
    }
}
