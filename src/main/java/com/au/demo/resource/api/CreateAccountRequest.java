package com.au.demo.resource.api;

import org.springframework.stereotype.Component;

@Component
public class CreateAccountRequest {
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    private String accountType;
}
