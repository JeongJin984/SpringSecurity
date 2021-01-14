package com.example.SpringSecurity2.Controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "login")
public class LoginController {

    @GetMapping()
    public String login() {
        return "login";
    }

}
