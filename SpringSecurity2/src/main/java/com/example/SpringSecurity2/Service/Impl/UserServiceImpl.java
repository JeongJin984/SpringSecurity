package com.example.SpringSecurity2.Service.Impl;

import com.example.SpringSecurity2.Service.UserService;
import com.example.SpringSecurity2.domain.entity.Account;
import com.example.SpringSecurity2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
