package com.kanban.userAuthService.service;

import com.kanban.userAuthService.domain.KanbanUser;

import java.util.Map;

public interface UserAuthService {
    Map<String,String> login(String email, String password);
//     String login(String email, String password);
    KanbanUser register(KanbanUser kanbanUser);
    KanbanUser getUserById(String email);
    KanbanUser changePassword(String email,String oldPass,String newPassword);
}
