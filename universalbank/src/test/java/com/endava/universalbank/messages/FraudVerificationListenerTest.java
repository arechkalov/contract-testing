package com.endava.universalbank.messages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE,
    properties = "spring.cloud.stream.bindings.input.destination=notification")
@AutoConfigureStubRunner(ids = {"com.endava:fraud-service:+:stubs:6565"},
    stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class FraudVerificationListenerTest {

    @Autowired
    StubFinder stubFinder;

    @Autowired
    private FraudVerificationListener listener;

    @Test
    public void shouldIncreaseFraudCounterWhenFraudTookPlace() {
        int initialCount = listener.fraudCounter.get();

        stubFinder.trigger("notification_fraud_message");

        then(listener.fraudCounter.get()).isGreaterThan(initialCount);

    }
}