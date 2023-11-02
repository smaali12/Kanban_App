package com.example.mailing_service.service;

import com.example.mailing_service.dto.KanbanUser;
import com.example.mailing_service.proxy.UserCredentialProxy;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PasswordForgotService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserCredentialProxy userCredentialProxy;


    @RabbitListener(queues = "fmail-queue")
    public void sendForgotMail(String email) {
        String[] e=email.split("\"");
        String emailId=e[1];
//        System.out.println(e[1]);
        KanbanUser kanbanUser=userCredentialProxy.getUserById(emailId);
//        System.out.println(kanbanUser.getEmail());
//        System.out.println(kanbanUser.getPassword());
//        String body="Hello, we have received a request for you password of "+kanbanUser.getEmail()+"\n"+"Dont Worry."+"Your password is "+kanbanUser.getPassword();

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("letreroapp@gmail.com");
        message.setTo(kanbanUser.getEmail());
        String body="Dear User"+"\n"
                +"\n"+kanbanUser.getEmail()
                +"\n"
                +"We have received a request for your password"+"\n"
                +"{ Your password is "+kanbanUser.getPassword()+" }"+"\n"
                +"\n"+"\n"
                +"Love you bby my lovely husband by NethraPoovarasan"
                +"Regards" +"\n"
                +"Team Letrero"  +"\n"+"\n"+"\n"
                +"Visit us at: http://localhost:4200/ **" +"\n"
                +"Follow us on: http://www.twitter.com/letrero" +"\n"
                +"--------------------------------------------------------------------------------------------------"  +"\n"
                +"DISCLAIMER" +"\n"
                +"This email and any files transmitted with it are confidential and are solely for the use of the " +
                "individual or entity to which it is addressed. Any use, distribution, copying or disclosure by any " +
                "other person is strictly prohibited. If you receive this transmission in error, please notify the " +
                "sender by reply email and then destroy the message. Opinions, conclusions and other information in " +
                "this message that do not relate to official business of the company shall be understood to be neither " +
                "given nor endorsed by Letrero Ltd. Any information contained in this email, when addressed to Clients is " +
                "subject to the terms and conditions in governing client contract.";
        message.setText(body);
        String subject="Regarding your request for password";
        message.setSubject(subject);
        javaMailSender.send(message);
        System.out.println("mail sent successfully");
    }
}