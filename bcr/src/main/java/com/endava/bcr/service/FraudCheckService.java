package com.endava.bcr.service;

import com.endava.bcr.dto.fraud.FraudServiceRequest;
import com.endava.bcr.dto.fraud.FraudServiceResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FraudCheckService {

    private final RestTemplate restTemplate;

    private int port = 6565;

    public FraudCheckService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
            .errorHandler(new CustomResponseHandler())
            .build();
    }

    public ResponseEntity<FraudServiceResponse> check(FraudServiceRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return restTemplate.exchange("http://localhost:" + port + "/fraudcheck", HttpMethod.PUT, new HttpEntity<>(request, httpHeaders),
            FraudServiceResponse.class);
    }
}
