package com.endava.fraud.bank;

import com.endava.fraud.FraudDetectionController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class BankBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }

	public void assertThatRejectionReasonIsNull(Object rejectionReason) {
		assert rejectionReason == null;
	}
}