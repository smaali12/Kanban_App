package com.example.mailing_service.proxy;

import com.example.mailing_service.dto.KanbanUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="User-Auth-Service",url ="localhost:8081" )
    public interface UserCredentialProxy {
        @GetMapping("/api/v1/getUserById/{email}")
        public KanbanUser getUserById(@PathVariable String email);
    }

