package com.example.SpringSecurity2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SImpleController {

    @GetMapping(value="/")
    public String home() throws Exception {
        return "home";
    }

}
