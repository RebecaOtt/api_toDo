package com.teach.api.toDo.service;

import com.teach.api.toDo.domain.StatusTask;
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

        return list.stream().map(TasksDTORes::new).toList();
    }

    private User findByIdEntity(Long id){
        try {
            return this.userRepository.findById(id).orElseThrow(()->
                    new ClassNotFoundException("Tasks not found!!"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
