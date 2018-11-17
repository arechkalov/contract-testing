package com.endava.fraud.bcr;

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
public class BcrBase {

    @Mock
    private NotificationService fraudCheckingService;

    @Before
    public void setup() {
        doNothing().when(fraudCheckingService).sendNotification(FRAUD_CODE, "2222220000");
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController(fraudCheckingService));
    }

}