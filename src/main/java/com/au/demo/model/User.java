package com.au.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String email;
    private BigDecimal monthlySalary;
    private BigDecimal monthlyExpense;
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
