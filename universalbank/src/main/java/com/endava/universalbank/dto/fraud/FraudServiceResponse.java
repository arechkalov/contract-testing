package com.endava.universalbank.dto.fraud;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FraudServiceResponse {

	private FraudCheckStatus fraudCheckStatus;

	@JsonProperty(value = "rejection.reason", required = true)
	private String rejectionReason;
}
