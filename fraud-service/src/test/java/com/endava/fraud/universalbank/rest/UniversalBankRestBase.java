package com.endava.fraud.universalbank.rest;

import com.endava.fraud.FraudDetectionController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class UniversalBankRestBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }

	public void assertThatRejectionReasonIsNull(Object rejectionReason) {
		assert rejectionReason == null;
	}
}