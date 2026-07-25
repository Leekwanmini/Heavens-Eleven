package com.heavenseleven.heavens_eleven.controller;

import com.heavenseleven.heavens_eleven.model.Course;
import com.heavenseleven.heavens_eleven.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @GetMapping
    public String listCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "courseName") String sort,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(required = false) String search,
            Model model) {

        Sort.Direction sortDir = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sort));

        Page<Course> coursePage;
        if (search != null && !search.trim().isEmpty()) {
            coursePage = courseRepository.findByCourseNameContainingIgnoreCase(search.trim(), pageable);
        } else {
            coursePage = courseRepository.findAll(pageable);
        }

        model.addAttribute("courses", coursePage.getContent());
        model.addAttribute("totalPages", coursePage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);
        return "courses";
    }
}