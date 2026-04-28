package com.teach.api.toDo.dto.res;

public record LoginResDTO (
        String type,
        String token,
        Long expiresAt
){
}
