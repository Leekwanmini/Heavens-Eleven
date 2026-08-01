package com.heavenseleven.heavens_eleven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String displayLogIn() {
        return "login";
    }
}
