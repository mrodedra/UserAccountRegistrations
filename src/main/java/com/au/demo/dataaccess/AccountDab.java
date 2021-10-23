package com.au.demo.dataaccess;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="account")
public class AccountDab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long accountId;

    @ManyToOne()
    @JoinColumn(name="user_ref_key")
    private UserDab user;

    @Column(name="account_type")
    private String accountType;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public UserDab getUser() {
        return user;
    }

    public void setUser(UserDab user) {
        this.user = user;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
