package com.teach.api.toDo.repository;

import com.teach.api.toDo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    //comparo o username com o que está no bd
    Optional<User> findByUsername(String username);
}