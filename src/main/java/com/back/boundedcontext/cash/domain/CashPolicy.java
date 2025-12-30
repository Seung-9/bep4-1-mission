package com.back.boundedcontext.cash.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CashPolicy {
    public static int HOLDING_MEMBER_ID;

    @Value("${spring.application.name}")
    public void setHoldingMemberId(int id) {
        HOLDING_MEMBER_ID = id;
    }
}