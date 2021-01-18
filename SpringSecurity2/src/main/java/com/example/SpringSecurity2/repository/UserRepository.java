package com.example.SpringSecurity2.repository;

import com.example.SpringSecurity2.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    int countByUsername(String username);
}
