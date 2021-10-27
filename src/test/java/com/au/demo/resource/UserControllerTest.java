package com.au.demo.resource;

import com.au.demo.exception.BusinessException;
import com.au.demo.model.Account;
import com.au.demo.model.User;
import com.au.demo.resource.api.CreateAccountRequest;
import com.au.demo.resource.api.CreateUserRequest;
import com.au.demo.resource.api.UpdateUserRequest;
import com.au.demo.service.AccountService;
import com.au.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserControllerTest {
    @Mock
    UserService userService;

    @Mock
    AccountService accountService;

    @InjectMocks
    UserController userController;

    private ResponseEntity<User> expectedUser;
    private ResponseEntity<List<User>> users;
    private ResponseEntity<User> user;
    private ResponseEntity<User> updateUser;
    private ResponseEntity<Account> expectedAccount;
    private ResponseEntity<List<Account>> accounts;

    @Before
    public void setup() throws BusinessException {
        updateUser=getUpdateUser();
        user=getUser_OK();
        expectedUser=getUser();
        users=getUsers();
        expectedAccount=getAccount();
        accounts=getAccounts();
        when(userService.createUser(any(CreateUserRequest.class))).thenReturn(expectedUser);
        when(userService.getUsers()).thenReturn(users);
        when(userService.getUser(any(Long.class))).thenReturn(user);
        when(userService.deleteUser(any(Long.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(userService.updateUser(any(Long.class), any(UpdateUserRequest.class))).thenReturn(updateUser);
        when(accountService.createAccount(any(Long.class),
                any(CreateAccountRequest.class))).thenReturn(expectedAccount);
        when(accountService.getAccounts(any(Long.class))).thenReturn(accounts);
    }

    @Test
    public void createUserTest_Success() throws BusinessException {
        CreateUserRequest createUserRequest=new CreateUserRequest();
        createUserRequest.setEmail("user1@gmail.com");
        createUserRequest.setName("user1");
        createUserRequest.setMonthlySalary(new BigDecimal(3500));
        createUserRequest.setMonthlyExpense(new BigDecimal(2000));
        ResponseEntity<User> actualUser=userController.createUser(createUserRequest);
        assertNotNull(actualUser);
        assertEquals(HttpStatus.CREATED,actualUser.getStatusCode());
        assertEquals(expectedUser.getBody().getName(),actualUser.getBody().getName());
    }

    @Test
    public void createUserTest_Fail() throws BusinessException {
        when(userService.createUser(any(CreateUserRequest.class))).
                thenThrow(new BusinessException(BusinessException.USER_EMAIL_EXISTS_CODE,
                        BusinessException.USER_EMAIL_EXISTS_MESSAGE));

        CreateUserRequest createUserRequest=new CreateUserRequest();
        createUserRequest.setEmail("user1@gmail.com");
        createUserRequest.setName("user1");
        createUserRequest.setMonthlySalary(new BigDecimal(3500));
        createUserRequest.setMonthlyExpense(new BigDecimal(2000));
        try{
            ResponseEntity<User> actualUser=userController.createUser(createUserRequest);
        } catch (BusinessException bx){
            assertEquals("Given email is already registered, please use different email",
                    bx.getErrorMessage());
        }

    }

    @Test
    public void getUsersTest_Success() {
        ResponseEntity<List<User>> actualUsers=userController.getUsers();
        assertNotNull(actualUsers);
        assertEquals(HttpStatus.OK,actualUsers.getStatusCode());
        assertEquals(expectedUser.getBody().getName(),actualUsers.getBody().get(0).getName());
    }

    @Test
    public void getUserByIdTest_Success() {
        ResponseEntity<User> actualUser=userController.getUser(10L);
        assertNotNull(actualUser);
        assertEquals(HttpStatus.OK,actualUser.getStatusCode());
        assertEquals(expectedUser.getBody().getName(),actualUser.getBody().getName());
        assertEquals(expectedUser.getBody().getEmail(),actualUser.getBody().getEmail());
    }

    @Test
    public void deleteUserTest_Success() {
        ResponseEntity<HttpStatus> deleteUser=userController.deleteUser(10L);
        assertNotNull(deleteUser);
        assertEquals(HttpStatus.OK,deleteUser.getStatusCode());
    }

    @Test
    public void updateUserTest_Success() throws BusinessException {
        UpdateUserRequest updateUserRequest=new UpdateUserRequest();
        updateUserRequest.setMonthlySalary(new BigDecimal(3500));
        updateUserRequest.setMonthlyExpense(new BigDecimal(2000));
        ResponseEntity<User> updateUser=userController.updateUser(10L,updateUserRequest);
        assertNotNull(updateUser);
        assertEquals(HttpStatus.OK,updateUser.getStatusCode());
        assertTrue(updateUser.getBody().getMonthlySalary().compareTo(updateUser.getBody().getMonthlyExpense())>0);
    }

    @Test
    public void createAccountTest_Success() throws BusinessException {
        CreateAccountRequest createAccountRequest=new CreateAccountRequest();
        createAccountRequest.setAccountType("Savings");
        ResponseEntity<Account> actualAccount=userController.createAccount(20L, createAccountRequest);
        assertNotNull(actualAccount);
        assertEquals(HttpStatus.CREATED,actualAccount.getStatusCode());
        assertEquals(expectedAccount.getBody().getAccountType(),actualAccount.getBody().getAccountType());
    }

    @Test
    public void createAccountTest_Fail() throws BusinessException {
        when(accountService.createAccount(any(Long.class), any(CreateAccountRequest.class)))
                .thenThrow(new BusinessException(BusinessException.ACCOUNT_MIN_BALANCE_CODE,
                        BusinessException.ACCOUNT_MIN_BALANCE_MESSAGE));
        CreateAccountRequest createAccountRequest=new CreateAccountRequest();
        createAccountRequest.setAccountType("Savings");
        try {
            ResponseEntity<Account> actualAccount = userController.createAccount(20L, createAccountRequest);
        }catch (BusinessException bx){
            assertEquals("Account can not be open due to minimum savings eligibility",bx.getErrorMessage());
        }
    }

    @Test
    public void getAccountsTest_Success() throws BusinessException {
        ResponseEntity<List<Account>> accounts=userController.getAccounts(10L);
        assertNotNull(accounts);
        assertEquals(HttpStatus.OK,accounts.getStatusCode());
        assertEquals("Savings",accounts.getBody().get(0).getAccountType());
    }

    private ResponseEntity<Account> getAccount(){
        Account account=new Account();
        account.setAccountId(20L);
        account.setAccountType("Savings");
        account.setUserId(10L);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    private ResponseEntity<List<Account>> getAccounts(){
        List<Account> accounts=new ArrayList<>();
        Account account=new Account();
        account.setAccountId(20L);
        account.setAccountType("Savings");
        account.setUserId(10L);
        accounts.add(account);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    private ResponseEntity<User> getUser(){
        User user=new User();
        user.setId(10L);
        user.setEmail("user1@gmail.com");
        user.setName("user1");
        user.setMonthlySalary(new BigDecimal(3500));
        user.setMonthlyExpense(new BigDecimal(2000));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private ResponseEntity<List<User>> getUsers(){
        List<User> users=new ArrayList<>();
        User user=new User();
        user.setId(10L);
        user.setEmail("user1@gmail.com");
        user.setName("user1");
        user.setMonthlySalary(new BigDecimal(3500));
        user.setMonthlyExpense(new BigDecimal(2000));
        users.add(user);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    private ResponseEntity<User> getUser_OK(){
        User user=new User();
        user.setId(10L);
        user.setEmail("user1@gmail.com");
        user.setName("user1");
        user.setMonthlySalary(new BigDecimal(3500));
        user.setMonthlyExpense(new BigDecimal(2000));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    private ResponseEntity<User> getUpdateUser(){
        User user=new User();
        user.setId(10L);
        user.setEmail("user1@gmail.com");
        user.setName("user1");
        user.setMonthlySalary(new BigDecimal(3500));
        user.setMonthlyExpense(new BigDecimal(2000));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
