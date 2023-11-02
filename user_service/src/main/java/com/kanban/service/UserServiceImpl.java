package com.kanban.service;

import com.kanban.domain.User;
import com.kanban.domain.UserWithPassword;
import com.kanban.dto.UserCredential;
import com.kanban.filter2.TokenFilterByBody;
import com.kanban.proxy.UserCredentialProxy;
import com.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCredentialProxy userCredentialProxy;


    @Override
    public User register(UserWithPassword userWithPassword) {

        if (userRepository.findById(userWithPassword.getUser().getEmail()).isPresent())
        {
            return null;
        }else {
            userCredentialProxy.register(new UserCredential(userWithPassword.getUser().getEmail(),userWithPassword.getPassword()));
            return userRepository.save(userWithPassword.getUser());
        }
    }

    @Override
    public User getUserById(String token){
        if(token==null){
            return null;
        }
        else {
            String userId="";
            try {
                TokenFilterByBody tokenFilterByBody = new TokenFilterByBody();
                userId= tokenFilterByBody.doFilter2(token);

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            return userRepository.findById(userId).get();
        }
    }

    @Override
    public User addProject(String email, int projectId) {
        User user=userRepository.findById(email).get();
        if(!user.getProjectlist().contains(projectId)){
            user.getProjectlist().add(projectId);
            return userRepository.save(user);
        }
        else {
            return null;
        }
    }

    @Override
    public User deleteProject(String email, int projectId) {
        User user=userRepository.findById(email).get();
        if(user.getProjectlist().contains(projectId)){
            int index =user.getProjectlist().indexOf(projectId);
            user.getProjectlist().remove(index);
            return userRepository.save(user);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean isUserPresent(String emailId) {
        if(userRepository.findById(emailId).isPresent()){
            return true;
        }
        return false;
    }

}
