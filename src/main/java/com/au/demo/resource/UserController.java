package com.au.demo.resource;

import com.au.demo.exception.BusinessException;
import com.au.demo.model.Account;
import com.au.demo.model.User;
import com.au.demo.resource.api.CreateAccountRequest;
import com.au.demo.resource.api.CreateUserRequest;
import com.au.demo.resource.api.UpdateUserRequest;
import com.au.demo.service.AccountService;
import com.au.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@GetMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUsers() {
		return userService.getUsers();
	}

	@GetMapping(value="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);

	}
	
	@PostMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) throws BusinessException {
		return userService.createUser(createUserRequest);
	}

	@PutMapping(value="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest updateUserRequest) throws BusinessException {
		return userService.updateUser(id, updateUserRequest);
	}

	@DeleteMapping (value="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
		return userService.deleteUser(id);
	}

	@PostMapping(value="/users/{id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> createAccount(@PathVariable("id") Long userId, @RequestBody CreateAccountRequest createAccountRequest) throws BusinessException {
		return accountService.createAccount(userId,createAccountRequest);
	}

	@GetMapping(value="/users/{id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Account>> getAccounts(@PathVariable("id") Long userId) throws BusinessException {
		return accountService.getAccounts(userId);
	}
}
