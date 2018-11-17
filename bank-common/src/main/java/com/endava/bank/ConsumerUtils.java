package com.endava.bank;

import org.springframework.cloud.contract.spec.internal.ClientDslProperty;

public class ConsumerUtils {

    public static ClientDslProperty loanAmountIsBiggerThanLimit() {
        return new ClientDslProperty(PatternUtils.amountIsGreaterThan_50000(), 1000000000.01);
    }

    public static ClientDslProperty loanAmountIsLessThanLimit() {
        return new ClientDslProperty(PatternUtils.amountIsLessThan_50000(), 200.11);
    }
}
