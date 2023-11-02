package com.kanban.service;

import com.kanban.domain.User;
import com.kanban.domain.UserWithPassword;
import com.kanban.exception.UserAlreadyExistException;

public interface UserService {
     User register (UserWithPassword userWithPassword)throws UserAlreadyExistException;
     User getUserById(String token);
     User addProject(String email, int projectId);
     User deleteProject(String email, int projectId);
     boolean isUserPresent(String emailId);
}
