package com.kanban.filter2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class TokenFilterByBody {

    public String doFilter2(String token) throws UnsupportedEncodingException {
//        System.out.println(token);
        String payload=token.split("[.]")[1];
//        System.out.println(payload);
        String data=new String(Base64.decodeBase64(payload),"UTF-8");
//        System.out.println(data);
        String userId=data.split("\"")[3];
//        System.out.println(userId);
       return userId;
    }
}
