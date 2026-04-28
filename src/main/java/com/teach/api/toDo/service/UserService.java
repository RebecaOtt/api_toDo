package com.teach.api.toDo.service;

import com.teach.api.toDo.dto.req.LoginReqDTO;
import com.teach.api.toDo.dto.req.UserReqDTO;
import com.teach.api.toDo.dto.res.LoginResDTO;
import com.teach.api.toDo.dto.res.UserResDTO;
import com.teach.api.toDo.model.User;
import com.teach.api.toDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResDTO createUser(UserReqDTO dto) {
        return null;
    }

    public LoginResDTO login(LoginReqDTO dto) {
        return null;
    }

    public void validate(String username, String password) {
        if (username == null)
            throw  new RuntimeException("Username cannot be null");
        if (password == null)
            throw new RuntimeException("Password cannot be null");
        if (username.isEmpty())
            throw  new RuntimeException("Username cannot be empty");
        if (password.isEmpty())
            throw new RuntimeException("Password cannot be empty");
    }
}
