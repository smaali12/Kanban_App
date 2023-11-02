package com.kanban.userAuthService.service;

import java.util.Map;

public interface TokenGenerator {
    Map<String,String> getToken(String email);
//    String getToken(String email);

}
