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
import org.springframework.web.bind.annotation.PathVariable;
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
    //added from here
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + id));
        model.addAttribute("course", course);
        return "course-form";
    }

    @PostMapping("/{id}/edit")
    public String updateCourse(@PathVariable Long id, 
        @Valid @ModelAttribute("course") Course course, BindingResult result){
        if (result.hasErrors()){
            return "course-form";
        }
        course.setId(id);
        courseRepository.save(course);
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return "redirect:/courses";
    }//to here

    @GetMapping
    public String listCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "courseName") String sort,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "all") String filterType,
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
        model.addAttribute("totalElement", coursePage.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("hasPrevious", coursePage.hasPrevious());
        model.addAttribute("hasNext", coursePage.hasNext());

        model.addAttribute("search", search);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);
        model.addAttribute("filterType", filterType);
        
        return "courses";
    }
}