package com.au.demo.mapper;

import com.au.demo.dataaccess.UserDab;
import com.au.demo.model.User;
import com.au.demo.resource.api.CreateUserRequest;
import com.au.demo.resource.api.UpdateUserRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UserDab mapToUserDab(CreateUserRequest createUserRequest){
        UserDab userDab= new UserDab();
        userDab.setName(createUserRequest.getName());
        userDab.setEmail(createUserRequest.getEmail());
        userDab.setMonthlyExpense(createUserRequest.getMonthlyExpense());
        userDab.setMonthlySalary(createUserRequest.getMonthlySalary());
        userDab.setCreatedDate(LocalDateTime.now());
        return userDab;
    }

    public User mapToCreateUserResponse(UserDab userDab){
        User createUserResponse= new User();
        createUserResponse.setId(userDab.getId());
        createUserResponse.setName(userDab.getName());
        createUserResponse.setEmail(userDab.getEmail());
        createUserResponse.setMonthlyExpense(userDab.getMonthlyExpense());
        createUserResponse.setMonthlySalary(userDab.getMonthlySalary());
        createUserResponse.setCreatedDate(userDab.getCreatedDate());
        return createUserResponse;
    }
}
