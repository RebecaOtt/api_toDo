package com.teach.api.toDo.repository;

import com.teach.api.toDo.domain.StatusTask;
import com.teach.api.toDo.model.Task;
import com.teach.api.toDo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserAndStatus(User user, StatusTask statusTask);

    List<Task> findByUser(User user);
}
