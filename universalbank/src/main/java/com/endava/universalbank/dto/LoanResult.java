package com.endava.universalbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResult {

    private LoanStatus loanStatus;

    private String rejectionReason;
}
