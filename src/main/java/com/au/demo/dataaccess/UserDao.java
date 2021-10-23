package com.au.demo.dataaccess;

import com.au.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository <UserDab, Long> {
    UserDab findByEmail(String email);
}
