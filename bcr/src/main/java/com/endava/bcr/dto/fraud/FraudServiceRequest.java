package com.endava.bcr.dto.fraud;

import com.endava.bcr.dto.Loan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FraudServiceRequest {

    @JsonProperty("participant.id")
    private String participantId;

    private BigDecimal loanAmount;

    public FraudServiceRequest(Loan loan) {
        this.participantId = loan.getParticipant().getId();
        this.loanAmount = loan.getLoanAmount();
    }
}
