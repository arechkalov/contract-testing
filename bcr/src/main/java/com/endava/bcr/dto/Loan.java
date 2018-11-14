package com.endava.bcr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private Participant participant;

    private BigDecimal loanAmount;

}
