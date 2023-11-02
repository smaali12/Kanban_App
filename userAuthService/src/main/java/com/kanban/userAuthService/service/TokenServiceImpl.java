package com.kanban.userAuthService.service;

import com.kanban.userAuthService.domain.KanbanUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenGenerator{

    @Override
    public Map<String, String> getToken(String email) {
        String jwtToken= Jwts.builder().setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"securitykey").compact();
        Map<String,String> map=new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","user logged in");
        return map;
    }


//    @Override
//    public Map<String, String> generateToken(User user) {
//        Map<String,String> result=new HashMap<>();
//        user.setPassword("");
//
//        Map<String,Object> userdata=new HashMap<>();
//        userdata.put("user_role",user.getRole());
//        userdata.put("user_email",user.getEmailId());
//
//        String jwt= Jwts.builder()
//                .setClaims(userdata)
//                .setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS512,"mysecretkey")
//                .compact();
//        result.put("token",jwt);
//        result.put("message","user login success");
//        result.put("role",user.getRole());
//        return result;
//    }

//    @Override
//    public String getToken(String email) {
//        String jwtToken= Jwts.builder().setSubject(email)
//                .setIssuedAt(new Date())
//                .signWith(SignatureAlgorithm.HS256,"securitykey").compact();
//        return jwtToken;
//    }
}
