package com.teach.api.toDo.controller;

import com.teach.api.toDo.dto.req.LoginReqDTO;
import com.teach.api.toDo.dto.req.UserReqDTO;
import com.teach.api.toDo.dto.res.LoginResDTO;
import com.teach.api.toDo.dto.res.UserResDTO;
import com.teach.api.toDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResDTO createUser(
            @RequestBody UserReqDTO dto
    ){
        //Criar um usuario, chamo o service
        UserResDTO response = userService.createUser(dto);
        return response;
    }

    //vai ser chamada quando na uri for "localhost:8080//..../auth/login
    @PostMapping("/login")
    public LoginResDTO login(
            @RequestBody LoginReqDTO dto
    ){
        LoginResDTO response = userService.login(dto);
        return response;
    }
}
