package com.au.demo.service;

import com.au.demo.dataaccess.UserDab;
import com.au.demo.dataaccess.UserDao;
import com.au.demo.exception.BusinessException;
import com.au.demo.mapper.UserMapper;
import com.au.demo.model.User;
import com.au.demo.repository.UserRepository;
import com.au.demo.resource.api.CreateUserRequest;
import com.au.demo.resource.api.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDao userDao;

    public ResponseEntity<User> createUser(CreateUserRequest createUserRequest) throws BusinessException {
        validateRequest(createUserRequest);
        if(userRepository.getUserByEmail(createUserRequest.getEmail()) == null){
            UserDab userDab=userMapper.mapToUserDab(createUserRequest);
            UserDab persitedUserDab=userRepository.save(userDab);
            User user = userMapper.mapToCreateUserResponse(persitedUserDab);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            throw new BusinessException(BusinessException.USER_EMAIL_EXISTS_CODE, BusinessException.USER_EMAIL_EXISTS_MESSAGE);
        }
    }

    public ResponseEntity<List<User>> getUsers() {
        List<UserDab> userDabs= userRepository.getUsers();
        List<User> userList=new ArrayList<>();
        for(UserDab userDab : userDabs){
            userList.add(userMapper.mapToCreateUserResponse(userDab));
        }
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    public ResponseEntity<User> getUser(Long id) {
        Optional<UserDab> userDab = userRepository.getUser(id);
        if (userDab.isPresent()) {
            return new ResponseEntity<>(userMapper.mapToCreateUserResponse(userDab.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> updateUser(Long id,UpdateUserRequest updateUserRequest) throws BusinessException {
        Optional<UserDab> userDabOpt = userDao.findById(id);
        if(userDabOpt.isPresent()){
            UserDab userDab=userDabOpt.get();
            userDab.setMonthlySalary(updateUserRequest.getMonthlySalary());
            userDab.setMonthlyExpense(updateUserRequest.getMonthlyExpense());
            UserDab updatedUserDab=userRepository.update(userDab);
            return new ResponseEntity<>(userMapper.mapToCreateUserResponse(updatedUserDab), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<HttpStatus> deleteUser(Long id) {
        userRepository.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateRequest(CreateUserRequest createUserRequest) throws BusinessException{
        if(StringUtils.isEmpty(createUserRequest.getName())){
            throw new BusinessException(BusinessException.USER_NAME_CODE, BusinessException.USER_NAME_MESSAGE);
        }

        if(StringUtils.isEmpty(createUserRequest.getEmail())){
            throw new BusinessException(BusinessException.USER_EMAIL_CODE, BusinessException.USER_EMAIL_MESSAGE);
        }

        if(createUserRequest.getMonthlyExpense() == null){
            throw new BusinessException(BusinessException.USER_MONTHLY_EXPENSE_CODE, BusinessException.USER_MONTHLY_EXPENSE_MESSAGE);
        }

        if(createUserRequest.getMonthlySalary() == null){
            throw new BusinessException(BusinessException.USER_MONTHLY_SALARY_CODE, BusinessException.USER_MONTHLY_SALARY_MESSAGE);
        }
    }
}
