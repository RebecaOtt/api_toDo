package com.teach.api.toDo.dto.req;

import com.teach.api.toDo.domain.Roles;

//requisição de criar um usuario, iria mais coisas como email, cpf...
public record UserReqDTO(
        String username,
        String password,
        Roles role) {
}
