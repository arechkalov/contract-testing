package com.endava.fraud.universalbank.messaging;

import com.endava.fraud.Application;
import com.endava.fraud.messages.FraudCheckingService;
import com.endava.fraud.model.FraudCheck;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public abstract class UniversalBankMessagingBase {

    @Inject
    MessageVerifier messaging;

    @Autowired
    FraudCheckingService fraudCheckingService;

    @Before
    public void setUp(){
        this.messaging.receive("validation", 100, TimeUnit.MILLISECONDS);
    }

    public void participantIsFraud(){
        FraudCheck fraudCheck = new FraudCheck();
        fraudCheck.setParticipantId("1234567891");
        fraudCheck.setLoanAmount(BigDecimal.valueOf(60000));
        fraudCheckingService.participantIsFraud(fraudCheck);
    }

    public void participantIsNotFraud() {
        FraudCheck fraudCheck = new FraudCheck();
        fraudCheck.setParticipantId("1234567890");
        fraudCheck.setLoanAmount(BigDecimal.TEN);
        fraudCheckingService.participantIsFraud(fraudCheck);
    }

//    protected void assert_something() {
//        //assert something happened
//    }
}
