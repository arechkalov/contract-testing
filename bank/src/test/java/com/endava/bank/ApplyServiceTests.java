package com.endava.bank;

import com.endava.bank.dto.ApplyResult;
import com.endava.bank.dto.ApplyStatus;
import com.endava.bank.dto.BankApply;
import com.endava.bank.dto.Participant;
import com.endava.bank.service.ApplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.endava:fraud-service:+:stubs:6565"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@DirtiesContext
public class ApplyServiceTests {

    @Autowired
    private ApplyService service;

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
