package com.endava.thebank;

import com.endava.thebank.dto.LoanResult;
import com.endava.thebank.dto.LoanStatus;
import com.endava.thebank.dto.Loan;
import com.endava.thebank.dto.Participant;
import com.endava.thebank.service.LoanService;
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
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        stubsPerConsumer = true,
        consumerName = "bank")
@DirtiesContext
public class LoanServiceTests {

    @Autowired
    private LoanService service;

    @Test
    public void shouldBeRejectedDueToAbnormalLoanAmount() {
        // given:
        Loan application = new Loan(new Participant("2222222222"),
                99999);
        // when:
        LoanResult loanApplication = service.apply(application);
        // then:
        assertThat(loanApplication.getLoanStatus())
                .isEqualTo(LoanStatus.REJECTED);
    }
    // end::client_tdd[]
}
