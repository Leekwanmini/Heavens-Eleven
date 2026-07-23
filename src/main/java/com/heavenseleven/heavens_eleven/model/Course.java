package com.heavenseleven.heavens_eleven.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String roomNo;

    @NotBlank
    private String courseName;

    @Min(1)
    @Max(6)
    private int credits;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    
}