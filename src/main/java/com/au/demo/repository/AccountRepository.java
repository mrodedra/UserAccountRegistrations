package com.au.demo.repository;

import com.au.demo.dataaccess.AccountDab;
import com.au.demo.dataaccess.AccountDao;
import com.au.demo.dataaccess.UserDab;
import com.au.demo.mapper.AccountMapper;
import com.au.demo.model.Account;
import com.au.demo.resource.api.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountMapper accountMapper;

    public AccountDab save(AccountDab accountDab){
        return accountDao.save(accountDab);
    }

    public List<AccountDab> getAccounts(Long userId){
        return accountDao.findByUser(userId);
    }
}
