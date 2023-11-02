package com.kanban.userAuthService.service;

import com.kanban.userAuthService.domain.KanbanUser;
import com.kanban.userAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenServiceImpl tokenService;

//    @Override
//    public Map<String,String> login(String email, String password) {
//        Optional<KanbanUser> kanbanUser1=userRepository.findById(email);
//        if(kanbanUser1.isEmpty()) {
//            return null;
//        }
//        else {
//            if(!kanbanUser1.get().getPassword().equals(password)) {
//                return null;
//            }
//            else {
//                return tokenService.getToken(email);
//            }
//        }
//    }

    @Override
    public KanbanUser getUserById(String email){
        Optional<KanbanUser> kanbanUser=userRepository.findById(email);
        if(kanbanUser.isPresent()) {
            return userRepository.findById(email).get();
        }
        else {
            return null;
        }
    }

    @Override
    public Map<String, String> login(String email, String password) {
        Optional<KanbanUser> kanbanUser1=userRepository.findById(email);
        if(kanbanUser1.isEmpty()) {
            return null;
        }
        else {
            if(!kanbanUser1.get().getPassword().equals(password)) {
                return null;
            }
            else {
                return tokenService.getToken(email);
            }
        }
    }

    @Override
    public KanbanUser register(KanbanUser kanbanUser) {
        Optional<KanbanUser> kanbanUser1=userRepository.findById(kanbanUser.getEmail());
        if(kanbanUser1.isPresent()) {
            return null;
        }
        else {
            return userRepository.save(kanbanUser);
        }
    }

    @Override
    public KanbanUser changePassword(String email,String oldPass, String newPassword) {
        KanbanUser kanbanUser=getUserById(email);
        if(!kanbanUser.getPassword().equals(oldPass)){
            return null;
        }
        else {
            return userRepository.save(new KanbanUser(email,newPassword));
        }
    }
}
