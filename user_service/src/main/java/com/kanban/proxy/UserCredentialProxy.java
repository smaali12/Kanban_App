package com.kanban.proxy;

import com.kanban.dto.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="User-Auth-Service",url ="localhost:8081" )
public interface UserCredentialProxy {
    @PostMapping("/api/v1/registration")
    public ResponseEntity<?> register(@RequestBody UserCredential userCredential);
}
