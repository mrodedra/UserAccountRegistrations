package com.au.demo.repository;

import com.au.demo.dataaccess.UserDab;
import com.au.demo.dataaccess.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    UserDao userDao;

    public UserDab save(UserDab userDab){
        return userDao.save(userDab);
    }

    public UserDab update(UserDab userDab){
        return userDao.saveAndFlush(userDab);
    }

    public List<UserDab> getUsers(){
        return userDao.findAll();
    }

    public Optional<UserDab> getUser(Long id){
        return userDao.findById(id);
    }

    public void deleteUser(Long id){
        userDao.deleteById(id);
    }

    public  UserDab getUserByEmail(String email){
        return userDao.findByEmail(email);
    }
}
