package com.example.SpringSecurity2.Controller.user;

import com.example.SpringSecurity2.Service.UserService;
import com.example.SpringSecurity2.domain.Account;
import com.example.SpringSecurity2.domain.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value="/mypage")
    public String myPage() {
        return "user/mypage";
    }

    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDTO accountDTO) {

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDTO, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        userService.createUser(account);

        return "redirect:/";
    }
}
