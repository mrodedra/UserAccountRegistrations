package com.au.demo.mapper;

import com.au.demo.dataaccess.AccountDab;
import com.au.demo.dataaccess.UserDab;
import com.au.demo.model.Account;
import com.au.demo.resource.api.CreateAccountRequest;
import com.au.demo.resource.api.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountMapper {
    public AccountDab mapToAccountDab(CreateAccountRequest createAccountRequest){
        AccountDab accountDab= new AccountDab();
        accountDab.setAccountType(createAccountRequest.getAccountType());
        accountDab.setCreatedDate(LocalDateTime.now());
        return accountDab;
    }

    public Account mapToAccount(AccountDab accountDab){
        Account account= new Account();
        account.setAccountId(accountDab.getAccountId());
        account.setAccountType(accountDab.getAccountType());
        account.setUserId(accountDab.getUser().getId());
        return account;
    }
}
