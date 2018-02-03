package com.endava.bank.service;

import com.endava.bank.dto.ApplyResult;
import com.endava.bank.dto.ApplyStatus;
import com.endava.bank.dto.BankApply;
import com.endava.bank.dto.fraud.FraudCheckStatus;
import com.endava.bank.dto.fraud.FraudServiceRequest;
import com.endava.bank.dto.fraud.FraudServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplyService {

    private final RestTemplate restTemplate;

    private int port = 6565;

    @Autowired
    public ApplyService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ApplyResult apply(BankApply bankApply) {
        FraudServiceRequest request = new FraudServiceRequest(bankApply);

        FraudServiceResponse response = sendRequestToFraudDetectionService(request);

        return buildResponseFromFraudResult(response);
    }

    private FraudServiceResponse sendRequestToFraudDetectionService(FraudServiceRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // tag::client_call_server[]
        ResponseEntity<FraudServiceResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/fraudcheck", HttpMethod.PUT,
                new HttpEntity<>(request, httpHeaders),
                FraudServiceResponse.class);
        // end::client_call_server[]

        return response.getBody();
    }

    private ApplyResult buildResponseFromFraudResult(FraudServiceResponse response) {
        ApplyStatus applyStatus = null;
        if (FraudCheckStatus.OK == response.getFraudCheckStatus()) {
            applyStatus = ApplyStatus.APPLIED;
        } else if (FraudCheckStatus.FRAUD == response.getFraudCheckStatus()) {
            applyStatus = ApplyStatus.REJECTED;
        }

        return new ApplyResult(applyStatus, response.getRejectionReason());
    }

}
