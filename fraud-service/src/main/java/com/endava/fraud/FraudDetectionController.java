package com.endava.fraud;

import com.endava.fraud.model.FraudCheck;
import com.endava.fraud.model.FraudCheckResult;
import com.endava.fraud.model.FraudCheckStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class FraudDetectionController {

    public static final BigDecimal MAX_AMOUNT = new BigDecimal("50000");

    @RequestMapping(value = "/fraudcheck", method = PUT)
    public ResponseEntity<FraudCheckResult> fraudCheck(@RequestBody FraudCheck fraudCheck) {
        if(fraudCheck.getParticipantId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (amountGreaterThanThreshold(fraudCheck)) {
            return ResponseEntity.ok(new FraudCheckResult(FraudCheckStatus.FRAUD, "Amount too high"));
        }
        return ResponseEntity.ok(new FraudCheckResult(FraudCheckStatus.OK, null));
    }

    private boolean amountGreaterThanThreshold(FraudCheck fraudCheck) {
        return MAX_AMOUNT.compareTo(fraudCheck.getLoanAmount()) < 0;
    }

}
