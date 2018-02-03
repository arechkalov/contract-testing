package com.endava.contractdriven.dto;

public class ApplyResult {

	private ApplyStatus applyStatus;

	private String rejectionReason;

	public ApplyResult() {
	}

	public ApplyResult(ApplyStatus applyStatus, String rejectionReason) {
		this.applyStatus = applyStatus;
		this.rejectionReason = rejectionReason;
	}

	public ApplyStatus getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(ApplyStatus applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
}
