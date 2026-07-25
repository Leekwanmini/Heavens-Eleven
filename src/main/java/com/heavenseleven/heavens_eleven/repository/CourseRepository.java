package com.heavenseleven.heavens_eleven.repository;

import com.heavenseleven.heavens_eleven.model.Course;
import com.heavenseleven.heavens_eleven.model.Semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByCourseNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Course> findBySemester(Semester semester, Pageable pageable);
}