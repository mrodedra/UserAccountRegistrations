package com.au.demo.service;

import com.au.demo.dataaccess.AccountDab;
import com.au.demo.dataaccess.UserDab;
import com.au.demo.exception.BusinessException;
import com.au.demo.mapper.AccountMapper;
import com.au.demo.model.Account;
import com.au.demo.repository.AccountRepository;
import com.au.demo.repository.UserRepository;
import com.au.demo.resource.api.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountMapper accountMapper;

    final private static BigDecimal MIN_BALANCE=BigDecimal.valueOf(1000.0);

    public ResponseEntity<Account> createAccount(Long userId,CreateAccountRequest createAccountRequest) throws BusinessException {
        Optional<UserDab> userDab = userRepository.getUser(userId);
        if(userDab.isPresent()){
            if(!isEligibleToOpenAccount(userDab.get())){
                throw new BusinessException(BusinessException.ACCOUNT_MIN_BALANCE_CODE, BusinessException.ACCOUNT_MIN_BALANCE_MESSAGE);
            }
            AccountDab accountDab=accountMapper.mapToAccountDab(createAccountRequest);
            accountDab.setUser(userDab.get());
            userDab.get().getAccounts().add(accountDab);
            AccountDab persitedAccountDab=accountRepository.save(accountDab);
            Account account= accountMapper.mapToAccount(persitedAccountDab);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Account>> getAccounts(Long userId) throws BusinessException {
        Optional<UserDab> userDab=userRepository.getUser(userId);
        List<Account> accounts=new ArrayList<>();
        if(userDab.isPresent()){
            List<AccountDab> accountDabs=userDab.get().getAccounts();
            for(AccountDab accountDab : accountDabs){
                accounts.add(accountMapper.mapToAccount(accountDab));
            }
        } else {
            throw new BusinessException(BusinessException.NO_ACCOUNT_FOUND_CODE, BusinessException.NO_ACCOUNT_FOUND_MESSAGE);
        }
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }

    private boolean isEligibleToOpenAccount(UserDab userDab){
        if(userDab.getMonthlySalary() != null && userDab.getMonthlyExpense() != null
        && (userDab.getMonthlySalary().subtract(userDab.getMonthlyExpense()).compareTo(MIN_BALANCE) >= 0)){
            return true;
        }
        return false;
    }
}
