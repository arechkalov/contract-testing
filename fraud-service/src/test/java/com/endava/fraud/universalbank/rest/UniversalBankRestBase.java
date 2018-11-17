package com.endava.fraud.universalbank.rest;

import com.endava.fraud.messages.NotificationService;
import com.endava.fraud.web.FraudDetectionController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.endava.fraud.web.FraudDetectionController.FRAUD_CODE;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class UniversalBankRestBase {

    @Mock
    private NotificationService fraudCheckingService;

    @Before
    public void setup() {
        doNothing().when(fraudCheckingService).sendNotification(FRAUD_CODE, "1111110000");
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController(fraudCheckingService));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}