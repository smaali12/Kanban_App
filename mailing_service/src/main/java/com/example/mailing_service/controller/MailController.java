package com.example.mailing_service.controller;

import com.example.mailing_service.service.PasswordForgotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v4/")
public class MailController {

//    @Autowired
//    private PasswordForgotService passwordForgotService;
//
//    @PostMapping("mail/{receiver}")
//    public ResponseEntity<?> sendEmail(@PathVariable String receiver){
//       UserCredential result= passwordForgotService.getUser(receiver);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
