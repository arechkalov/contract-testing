package com.endava.universalbank.service;

import com.endava.universalbank.dto.fraud.FraudServiceRequest;
import com.endava.universalbank.dto.fraud.FraudServiceResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FraudCheckService {

    private final RestTemplate restTemplate;

    private int port = 6565;

    public FraudCheckService(RestTemplateBuilder builder) {
        this.restTemplate = builder.errorHandler(new CustomResponseHandler()).build();
    }

    @PutMapping("/fraudcheck")
    public ResponseEntity<FraudServiceResponse> check(FraudServiceRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return restTemplate.exchange("http://localhost:" + port + "/fraudcheck",
            HttpMethod.PUT, new HttpEntity<>(request, httpHeaders),
            FraudServiceResponse.class);
    }
}
