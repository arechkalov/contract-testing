package com.endava.fraud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudCheckResult {

    private FraudCheckStatus fraudCheckStatus;

    @JsonProperty("rejection.reason")
    private String rejectionReason;
}
