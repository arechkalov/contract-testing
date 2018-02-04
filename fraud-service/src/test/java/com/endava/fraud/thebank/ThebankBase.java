package com.endava.fraud.thebank;

import com.endava.fraud.FraudDetectionController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class ThebankBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }

}