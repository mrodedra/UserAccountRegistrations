package com.au.demo.resource.api;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateUserRequest {
    private String name;
    private String email;
    private BigDecimal monthlySalary;
    private BigDecimal monthlyExpense;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public BigDecimal getMonthlyExpense() {
        return monthlyExpense;
    }

    public void setMonthlyExpense(BigDecimal monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }
}
