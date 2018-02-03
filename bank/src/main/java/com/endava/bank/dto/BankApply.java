package com.endava.bank.dto;

import java.math.BigDecimal;

public class BankApply {

	private Participant participant;

	private BigDecimal amount;

	private String applyId;

	public BankApply() {
	}

	public BankApply(Participant participant, double amount) {
		this.participant = participant;
		this.amount = BigDecimal.valueOf(amount);
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
}
