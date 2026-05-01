package com.teach.api.toDo.dto.req;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.model.Task;

public record TasksDTOReq(String title, String description, StatusTask statusTask) {
    public Task dtoToModel(){
        return new Task(this.title(), this.description(), this.statusTask());
    }
}
