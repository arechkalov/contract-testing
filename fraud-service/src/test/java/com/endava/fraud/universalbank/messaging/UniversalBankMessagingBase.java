package com.endava.fraud.universalbank.messaging;

import com.endava.fraud.FraudServiceApplication;
import com.endava.fraud.messages.NotificationService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

import static com.endava.fraud.web.FraudDetectionController.FRAUD_CODE;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FraudServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public abstract class UniversalBankMessagingBase {

    @Inject
    MessageVerifier messaging;

    @Autowired
    NotificationService notificationService;

    int initialCount;

    @Before
    public void setUp() {
        this.messaging.receive("notification", 100, TimeUnit.MILLISECONDS);
    }

    public void sendNotification() {
        initialCount = notificationService.counter.get();
        notificationService.sendNotification(FRAUD_CODE, "1111110000");
    }

    protected void assert_something() {
        then(notificationService.counter.get()).isGreaterThan(initialCount);
    }
}
