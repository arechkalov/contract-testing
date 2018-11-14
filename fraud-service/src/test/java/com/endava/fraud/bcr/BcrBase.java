package com.endava.fraud.bcr;

import com.endava.fraud.FraudDetectionController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class BcrBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }

}