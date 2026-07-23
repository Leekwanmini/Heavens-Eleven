package com.heavenseleven.heavens_eleven.controller;

import com.heavenseleven.heavens_eleven.model.Course;
import com.heavenseleven.heavens_eleven.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("course", new Course());
        return "course-form";
    }

    @PostMapping
    public String submitForm(@Valid @ModelAttribute("course") Course course,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "course-form";
        }
        courseRepository.save(course);
        return "redirect:/courses";
    }
}