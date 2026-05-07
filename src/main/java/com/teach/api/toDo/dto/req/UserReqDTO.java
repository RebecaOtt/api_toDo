package com.teach.api.toDo.dto.req;

import com.teach.api.toDo.domain.Role;

public record UserReqDTO(
        String username,
        String password,
        Role role) {
}
