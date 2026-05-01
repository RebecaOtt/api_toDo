package com.teach.api.toDo.controller;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.dto.res.TasksDTORes;
import com.teach.api.toDo.model.User;
import com.teach.api.toDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TasksDTORes>> findAllTask(@RequestParam(required = false) StatusTask statusTask,
    @AuthenticationPrincipal User user){
        List<TasksDTORes> list = this.taskService.findAllTask(user.getId(), statusTask);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
