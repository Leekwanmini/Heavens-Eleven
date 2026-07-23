package com.heavenseleven.heavens_eleven.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String roomNo; // e.g. "N213"

    @NotNull
    @Min(0)
    @Max(3)
    private int credits;//new field added for range field

    @NotNull
    @Enumerated(EnumType.STRING)
    private Semester semester; // new field added for categorical field

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2,4}-\\d{3}$", message = "Course name must be like CPAN-228")
    private String courseName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public Semester getSemester() { return semester; }
    public void setSemester(Semester semester) { this.semester = semester; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}