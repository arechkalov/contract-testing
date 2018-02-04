package com.endava.thebank.service;

import com.endava.thebank.dto.Loan;
import com.endava.thebank.dto.LoanResult;
import com.endava.thebank.dto.LoanStatus;
import com.endava.thebank.dto.fraud.FraudCheckStatus;
import com.endava.thebank.dto.fraud.FraudServiceRequest;
import com.endava.thebank.dto.fraud.FraudServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanService {

    private final RestTemplate restTemplate;

    private int port = 6565;

    @Autowired
    public LoanService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public LoanResult apply(Loan loan) {
        FraudServiceRequest request = new FraudServiceRequest(loan);

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

    private LoanResult buildResponseFromFraudResult(FraudServiceResponse response) {
        LoanStatus loanStatus = null;
        if (FraudCheckStatus.OK == response.getFraudCheckStatus()) {
            loanStatus = LoanStatus.APPLIED;
        } else if (FraudCheckStatus.FRAUD == response.getFraudCheckStatus()) {
            loanStatus = LoanStatus.REJECTED;
        }

        return new LoanResult(loanStatus);
    }

}
