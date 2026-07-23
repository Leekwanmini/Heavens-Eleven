package com.heavenseleven.heavens_eleven.repository;

import com.heavenseleven.heavens_eleven.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}