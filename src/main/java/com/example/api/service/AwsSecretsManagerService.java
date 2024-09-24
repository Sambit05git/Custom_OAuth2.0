package com.example.api.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class AwsSecretsManagerService {

    private final SecretsManagerClient secretsManagerClient;

    public AwsSecretsManagerService() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                .region(Region.of("ap-south-1")) // Ensure this matches your AWS region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public List<Map<String, String>> getClientCredentials() throws Exception {
        String secretName = "oauth2-client-secrets";

        GetSecretValueRequest secretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse secretValueResponse = secretsManagerClient.getSecretValue(secretValueRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<Map<String, String>>> secretMap = objectMapper.readValue(secretValueResponse.secretString(), Map.class);
        return secretMap.get("clients");
    }
}
