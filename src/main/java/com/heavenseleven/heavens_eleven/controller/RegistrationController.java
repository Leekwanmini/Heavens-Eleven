package com.heavenseleven.heavens_eleven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.heavenseleven.heavens_eleven.model.User;
import com.heavenseleven.heavens_eleven.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String displayRegister(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        
        if (result.hasErrors()) {
            return "register";
        }

        if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
            result.rejectValue("username", "username.error", "Username already exists");
            return "register";
        } else {
            User newUser = new User();

            newUser.setRole("STUDENT");
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setUsername(user.getUsername());

            userRepository.save(newUser);
            return "redirect:/login";
        }
    }
}