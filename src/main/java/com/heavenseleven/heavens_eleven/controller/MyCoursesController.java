package com.heavenseleven.heavens_eleven.controller;

import com.heavenseleven.heavens_eleven.model.User;
import com.heavenseleven.heavens_eleven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyCoursesController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my-courses")
    public String myCourses(Authentication authentication, Model model){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + username));

        model.addAttribute("courses", user.getRegisteredCourses());
        return "my-courses";
    }
}