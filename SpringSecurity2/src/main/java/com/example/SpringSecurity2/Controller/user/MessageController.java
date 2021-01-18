package com.example.SpringSecurity2.Controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class MessageController {

    @GetMapping("/messages")
    public String messages() throws Exception {
        return "/user/messages";
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public String apiMessages() throws Exception {
        return "message_ok";
    }
}
