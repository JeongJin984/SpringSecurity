package com.example.SpringSecurity2.Service;

import com.example.SpringSecurity2.domain.dto.AccountDTO;
import com.example.SpringSecurity2.domain.entity.Account;

import java.util.List;

public interface UserService {
    void createUser(Account account);
}
