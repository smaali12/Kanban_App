package com.kanban.userAuthService.controller;

import com.kanban.userAuthService.config.MessageConfiguration;
import com.kanban.userAuthService.domain.KanbanUser;
import com.kanban.userAuthService.service.UserAuthServiceImpl;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;


import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class UserController {
    @Autowired
    UserAuthServiceImpl userAuthService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable String email,@PathVariable String password){
        System.out.println(email);
        System.out.println(password);
        Map<String, String> token=userAuthService.login(email,password);
        if(token==null){
            return null;
        }
        else {
            System.out.println(token);
            KanbanUser k=new KanbanUser(token.get("token"),"pass");
            return new ResponseEntity(token, HttpStatus.OK);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody KanbanUser kanbanUser){
        KanbanUser kanbanUser1=userAuthService.register(kanbanUser);
        if(kanbanUser1==null){
            return null;
        }
        else {
            return new ResponseEntity(kanbanUser1, HttpStatus.OK);
        }
    }

    @GetMapping("/getUserById/{email}")
    public KanbanUser getUserById(@PathVariable String email){
        return userAuthService.getUserById(email);
    }

    @PostMapping("/forgotpassword/{emailId}")
    public ResponseEntity<?> sendForgotPasswordMail(@PathVariable String emailId){
//        System.out.println("getting request");
        if(userAuthService.getUserById(emailId)!=null) {
            rabbitTemplate.convertAndSend("fmail-exchange", "forget-routing",emailId);
            JSONObject j=new JSONObject();
            j.put("status","success");
            return new ResponseEntity<>(j,HttpStatus.OK);
        }
        else {
            return null;
        }
    }

    @PostMapping("changePassword/{email}/{oldPass}/{newPass}")
    public ResponseEntity<?> changePassword(@PathVariable String email,@PathVariable String oldPass,@PathVariable String newPass){
        KanbanUser kanbanUser=userAuthService.changePassword(email,oldPass,newPass);
        return new ResponseEntity<>(kanbanUser,HttpStatus.OK);
    }
}
