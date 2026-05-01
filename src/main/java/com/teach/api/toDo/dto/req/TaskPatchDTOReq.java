package com.teach.api.toDo.dto.req;

import com.teach.api.toDo.domain.StatusTask;

public record TaskPatchDTOReq(StatusTask statusTask, String description) {
}
