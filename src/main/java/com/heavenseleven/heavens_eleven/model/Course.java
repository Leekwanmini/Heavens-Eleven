package com.heavenseleven.heavens_eleven.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(100)
    @Max(499)
    private int roomNo;

    @NotBlank
    private String courseName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getRoomNo() { return roomNo; }
    public void setRoomNo(int roomNo) { this.roomNo = roomNo; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

}