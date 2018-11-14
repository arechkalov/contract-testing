package com.endava.bcr.service;

import com.endava.bcr.dto.Loan;
import com.endava.bcr.dto.LoanResult;
import com.endava.bcr.dto.LoanStatus;
import com.endava.bcr.dto.fraud.FraudCheckStatus;
import com.endava.bcr.dto.fraud.FraudServiceRequest;
import com.endava.bcr.dto.fraud.FraudServiceResponse;
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
            return new LoanResult(LoanStatus.APPLIED);
        }
        return new LoanResult(LoanStatus.REJECTED);
    }

}
