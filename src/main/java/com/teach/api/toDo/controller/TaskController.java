package com.teach.api.toDo.controller;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.dto.req.TaskPatchDTOReq;
import com.teach.api.toDo.dto.req.TasksDTOReq;
import com.teach.api.toDo.dto.res.TasksDTORes;
import com.teach.api.toDo.model.User;
import com.teach.api.toDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TasksDTORes>> findAllTask(@RequestParam(required = false) StatusTask statusTask,
    @AuthenticationPrincipal User userId){
        List<TasksDTORes> list = this.taskService.findAllTask(userId.getId(), statusTask);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasksDTORes> findById(@PathVariable("id") Long id,
    @AuthenticationPrincipal User userId){
        TasksDTORes tasksModel = this.taskService.findById(userId.getId(), id);
        return ResponseEntity.ok(tasksModel);
    }

    @PostMapping
    public ResponseEntity<TasksDTORes> create(@RequestBody TasksDTOReq tasksDTOReq, UriComponentsBuilder uriComponentsBuilder, @AuthenticationPrincipal User userId) {
        TasksDTORes newTask = this.taskService.create(userId.getId(), tasksDTOReq);
        URI uri = uriComponentsBuilder.path("/tasks/{id}").buildAndExpand(newTask.id()
        ).toUri();

        return ResponseEntity.created(uri).body(newTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TasksDTORes> updateStatusOrDescription(@PathVariable("id") Long id, @RequestBody TaskPatchDTOReq taskPatchDTOReq, @AuthenticationPrincipal User userId){
        TasksDTORes tasksModel = this.taskService.updateStatusOrDescription(id, taskPatchDTOReq, userId.getId());
        return ResponseEntity.ok(tasksModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleted(@PathVariable("id") Long id, @AuthenticationPrincipal User userId) {
        this.taskService.deleted(id, userId.getId());
        return ResponseEntity.noContent().build();
    }

}
