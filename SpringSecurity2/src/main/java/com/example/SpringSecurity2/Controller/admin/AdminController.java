package com.example.SpringSecurity2.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/config")
    public String config(){
        return "admin/config";
    }

}
