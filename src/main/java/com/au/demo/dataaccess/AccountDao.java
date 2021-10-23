package com.au.demo.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDao extends JpaRepository<AccountDab, Long> {
    List<AccountDab> findByUser(Long userId);
}
