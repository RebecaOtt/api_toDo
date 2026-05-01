package com.teach.api.toDo.service;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.dto.req.TaskPatchDTOReq;
import com.teach.api.toDo.dto.req.TasksDTOReq;
import com.teach.api.toDo.dto.res.TasksDTORes;
import com.teach.api.toDo.model.Task;
import com.teach.api.toDo.model.User;
import com.teach.api.toDo.repository.TaskRepository;
import com.teach.api.toDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TasksDTORes> findAllTask(Long userId, StatusTask statusTask) {
        User user = this.findByIdEntity(userId);

        List<Task> list;
        if (statusTask != null) {
            list = this.taskRepository.findByUserAndStatus(user, statusTask);
        } else {
            list = this.taskRepository.findByUser(user);
        }

        return list.stream().map(TasksDTORes::ModelToDTO).toList();
    }

    public TasksDTORes findById(Long userId, Long id) {
        Task taskModel = this.findTaskByIdEntity(id);
        this.validatedUser(taskModel, userId);
        return TasksDTORes.ModelToDTO(taskModel);
    }

    private User findByIdEntity(Long id){
        try {
            return this.userRepository.findById(id).orElseThrow(()->
                    new ClassNotFoundException("User not found!!"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Task findTaskByIdEntity(Long id) {
        try {
            return this.taskRepository.findById(id).orElseThrow(()->
                    new ClassNotFoundException("Task not found!!"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void validatedUser(Task task, Long userId) {
        if (!task.getUser().getId().equals(userId)){
            throw new RuntimeException("task not found");
        }
    }

    public TasksDTORes create(Long userId, TasksDTOReq tasksDTOReq) {
        User user = this.findByIdEntity(userId);

        Task task = tasksDTOReq.dtoToModel();
        task.setUser(user);

        Task saveTask = this.taskRepository.save(task);
        return TasksDTORes.ModelToDTO(saveTask);
    }

    public TasksDTORes updateStatusOrDescription(Long id, TaskPatchDTOReq taskPatchDTOReq, Long userId) {
        Task task = this.findTaskByIdEntity(id);
        this.validatedUser(task, userId);

        if (taskPatchDTOReq.description() != null) {
            task.setDescription(taskPatchDTOReq.description());
        }
        if (taskPatchDTOReq.statusTask() != null) {
            task.setStatus(taskPatchDTOReq.statusTask());
        }

        Task updateTask = this.taskRepository.save(task);
        return TasksDTORes.ModelToDTO(updateTask);
    }

    public void deleted(Long id, Long userId) {
        Task task = this.findTaskByIdEntity(id);
        this.validatedUser(task, userId);
        this.taskRepository.delete(task);
    }
}
