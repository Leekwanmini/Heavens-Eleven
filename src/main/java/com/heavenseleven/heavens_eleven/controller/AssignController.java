package com.heavenseleven.heavens_eleven.controller;

import com.heavenseleven.heavens_eleven.model.Course;
import com.heavenseleven.heavens_eleven.model.User;
import com.heavenseleven.heavens_eleven.repository.CourseRepository;
import com.heavenseleven.heavens_eleven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssignController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/assign")
    public String showAssignForm(Model model){
        //Bring the list of students and teachers in database
        model.addAttribute("students", userRepository.findByRole("STUDENT"));
        model.addAttribute("courses", courseRepository.findAll());
        return "assign";
    }

    @PostMapping("/assign")
    public String assignCourse(@RequestParam Long studentId, @RequestParam Long courseId,Model model){
        //find objects using Id
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));
        //ASsign courses to students
        student.getRegisteredCourses().add(course);
        userRepository.save(student);

        model.addAttribute("students", userRepository.findByRole("STUDENT"));
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("message", "Course assigned successfully!");
        return "assign";
        
    }
}