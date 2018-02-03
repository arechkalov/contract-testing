package com.endava.contractdriven.dto.fraud;

import com.endava.contractdriven.dto.BankApply;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FraudServiceRequest {

	@JsonProperty("client.id")
	private String clientId;

	private BigDecimal loanAmount;

	public FraudServiceRequest() {
	}

	public FraudServiceRequest(BankApply loanApplication) {
		this.clientId = loanApplication.getParticipant().getName();
		this.loanAmount = loanApplication.getAmount();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
}
