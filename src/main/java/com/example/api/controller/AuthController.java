package com.example.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody Map<String, String> credentials) {
        // The actual token generation is handled by the OAuth2AuthorizationServerConfig
        return ResponseEntity.ok("Token generation handled by OAuth2AuthorizationServerConfig");
    }
}
