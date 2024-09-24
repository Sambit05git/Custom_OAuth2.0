package com.example.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientCredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final AwsSecretsManagerService awsSecretsManagerService;

    public ClientCredentialsService(PasswordEncoder passwordEncoder, AwsSecretsManagerService awsSecretsManagerService) {
        this.passwordEncoder = passwordEncoder;
        this.awsSecretsManagerService = awsSecretsManagerService;
    }

    public boolean validateCredentials(String id, String secret) {
        try {
            List<Map<String, String>> clients = awsSecretsManagerService.getClientCredentials();

            for (Map<String, String> client : clients) {
                String clientId = client.get("clientId");
                String clientSecret = client.get("clientSecret");

                if (clientId.equals(id) && passwordEncoder.matches(secret, clientSecret)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve client credentials", e);
        }
    }
}
