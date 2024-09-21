package com.example.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientCredentialsService {

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    private final PasswordEncoder passwordEncoder;

    public ClientCredentialsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validateCredentials(String id, String secret) {
        return clientId.equals(id) && passwordEncoder.matches(secret, clientSecret);
    }
}
