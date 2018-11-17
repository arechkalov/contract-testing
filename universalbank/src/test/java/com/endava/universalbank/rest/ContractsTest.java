package com.endava.universalbank.rest;

import com.endava.universalbank.dto.Loan;
import com.endava.universalbank.dto.Participant;
import com.endava.universalbank.dto.fraud.FraudCheckStatus;
import com.endava.universalbank.dto.fraud.FraudServiceRequest;
import com.endava.universalbank.dto.fraud.FraudServiceResponse;
import com.endava.universalbank.service.FraudCheckService;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.endava:fraud-service:+:stubs:6565"},
    stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    stubsPerConsumer = true,
    consumerName = "universalbank")
public class ContractsTest {

    private static final BigDecimal ONE_MILLIARD = BigDecimal.valueOf(1000000000.01);

    @Autowired
    private FraudCheckService fraudService;

    @Test
    public void shouldReturnFraudResponse() {
        Loan loan = new Loan(new Participant("1111110000"), ONE_MILLIARD);
        FraudServiceRequest request = new FraudServiceRequest(loan);

        ResponseEntity<FraudServiceResponse> response = fraudService.check(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFraudCheckStatus()).isEqualTo(FraudCheckStatus.FRAUD);
        assertThat(response.getBody().getRejectionReason()).isEqualTo("Amount too high");
    }

    @Test
    public void shouldReturnNonFraudResponse() {
        Loan loan = new Loan(new Participant("1111110000"), BigDecimal.valueOf(200.11));
        FraudServiceRequest request = new FraudServiceRequest(loan);

        ResponseEntity<FraudServiceResponse> response = fraudService.check(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFraudCheckStatus()).isEqualTo(FraudCheckStatus.OK);
        assertThat(response.getBody().getRejectionReason()).isNull();
    }

    @Test
    public void shouldReturnBadRequest() {
        Loan loan = new Loan(new Participant(null), BigDecimal.valueOf(200.11));
        FraudServiceRequest request = new FraudServiceRequest(loan);

        ResponseEntity<FraudServiceResponse> response = fraudService.check(request);

        assertThat(response)
            .has(new Condition<>(resp -> resp.getStatusCode().is4xxClientError(), null));
    }
}
