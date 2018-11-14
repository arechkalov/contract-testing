package com.endava.universalbank.service;

import com.endava.universalbank.dto.Loan;
import com.endava.universalbank.dto.LoanResult;
import com.endava.universalbank.dto.LoanStatus;
import com.endava.universalbank.dto.fraud.FraudCheckStatus;
import com.endava.universalbank.dto.fraud.FraudServiceRequest;
import com.endava.universalbank.dto.fraud.FraudServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class LoanService {

    private final FraudCheckService fraudCheckService;

    public LoanResult apply(Loan loan) {
        FraudServiceRequest request = new FraudServiceRequest(loan);

        ResponseEntity<FraudServiceResponse> fraudServiceResponseResponseEntity = fraudCheckService.check(request);

        return buildResponseFromFraudResult(Objects.requireNonNull(fraudServiceResponseResponseEntity.getBody()));
    }

    private LoanResult buildResponseFromFraudResult(FraudServiceResponse response) {
        if (FraudCheckStatus.OK == response.getFraudCheckStatus()) {
            return new LoanResult(LoanStatus.APPLIED, response.getRejectionReason());
        }
        return new LoanResult(LoanStatus.REJECTED, response.getRejectionReason());
    }

}
