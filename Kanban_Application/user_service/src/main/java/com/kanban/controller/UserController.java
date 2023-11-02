package com.kanban.controller;

import com.kanban.domain.User;
import com.kanban.domain.UserWithPassword;
import com.kanban.exception.UserAlreadyExistException;
import com.kanban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserWithPassword userWithPassword)throws UserAlreadyExistException {
        System.out.println(userWithPassword);
        User user=userService.register(userWithPassword);
        if (user==null){
//            throw new UserAlreadyExistException();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else

            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody String token){
        System.out.println(userService.getUserById(token));
        return new ResponseEntity<>(userService.getUserById(token),HttpStatus.OK);
    }

    @PostMapping("addProject/{email}/{projectId}")
    public ResponseEntity<?> addProjectToUser(@PathVariable String email,@PathVariable int projectId){
        return new ResponseEntity<>(userService.addProject(email,projectId),HttpStatus.OK);
    }

    @PostMapping("deleteTask/{email}/{projectId}")
    public ResponseEntity<?> deleteProjectFromUser(@PathVariable String email,@PathVariable int projectId){
        return new ResponseEntity<>(userService.deleteProject(email,projectId),HttpStatus.OK);
    }

    @GetMapping("checkUser/{emailId}")
    public ResponseEntity<?> isUserPresent(@PathVariable String emailId){
        return new ResponseEntity<>(userService.isUserPresent(emailId),HttpStatus.OK);
    }
}
