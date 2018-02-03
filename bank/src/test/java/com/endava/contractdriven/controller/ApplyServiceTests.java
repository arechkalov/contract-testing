package com.endava.contractdriven.controller;

import com.endava.contractdriven.dto.Participant;
import com.endava.contractdriven.dto.BankApply;
import com.endava.contractdriven.dto.ApplyResult;
import com.endava.contractdriven.dto.ApplyStatus;
import com.endava.contractdriven.service.ApplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// tag::autoconfigure_stubrunner[]
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.endava:my-contract-app:+:stubs:6565"},
        workOffline = true)
@DirtiesContext
public class ApplyServiceTests {

    @Autowired
    private ApplyService service;

    // tag::client_tdd[]
    @Test
    public void shouldBeRejectedDueToAbnormalLoanAmount() {
        // given:
        BankApply application = new BankApply(new Participant("1234567890"),
                99999);
        // when:
        ApplyResult loanApplication = service.apply(application);
        // then:
        assertThat(loanApplication.getApplyStatus())
                .isEqualTo(ApplyStatus.REJECTED);
        assertThat(loanApplication.getRejectionReason()).isEqualTo("Amount too high");
    }
    // end::client_tdd[]
}
