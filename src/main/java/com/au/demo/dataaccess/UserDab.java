package com.au.demo.dataaccess;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="user")
public class UserDab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="monthly_salary")
    private BigDecimal monthlySalary;

    @Column(name="monthly_expense")
    private BigDecimal monthlyExpense;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AccountDab> accounts;

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

    public List<AccountDab> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDab> accounts) {
        this.accounts = accounts;
    }
}
