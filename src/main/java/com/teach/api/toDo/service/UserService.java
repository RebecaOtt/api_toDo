package com.teach.api.toDo.service;

import com.teach.api.toDo.domain.Role;
import com.teach.api.toDo.dto.req.LoginReqDTO;
import com.teach.api.toDo.dto.req.UserReqDTO;
import com.teach.api.toDo.dto.res.LoginResDTO;
import com.teach.api.toDo.dto.res.UserResDTO;
import com.teach.api.toDo.exception.InvalidParamException;
import com.teach.api.toDo.exception.ResourceAlreadyExistsException;
import com.teach.api.toDo.exception.ResourceNotFoundException;
import com.teach.api.toDo.model.User;
import com.teach.api.toDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserResDTO createUser(UserReqDTO dto) {
        validate(dto.username(), dto.password());
        if (userRepository.existsByUsername(dto.username()))
            throw new ResourceAlreadyExistsException("Username already in use");

        User user = new User();

        user.setUsername(dto.username());

        String encondedPassword = passwordEncoder.encode(dto.password());
        user.setPassword(encondedPassword);

        List<Role> roles = List.of(dto.role());
        user.setRoles(roles);

        userRepository.save(user);
        return new UserResDTO(user.getId(), user.getUsername());
    }

    public LoginResDTO login(LoginReqDTO dto) {
        validate(dto.username(), dto.password());
        Optional<User> optionalUser = userRepository.findByUsername(dto.username());

        if (optionalUser.isEmpty())
            throw new ResourceNotFoundException("User not found");

        User user = optionalUser.get();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authenticationManager.authenticate(token);

        return tokenService.generateToken(user);
    }

    public void validate(String username, String password) {
        if (username == null)
            throw  new InvalidParamException("Username cannot be null");
        if (password == null)
            throw new InvalidParamException("Password cannot be null");
        if (username.isEmpty())
            throw  new InvalidParamException("Username cannot be empty");
        if (password.isEmpty())
            throw new InvalidParamException("Password cannot be empty");
    }
}
