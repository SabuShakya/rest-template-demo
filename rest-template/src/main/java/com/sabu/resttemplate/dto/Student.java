package com.sabu.resttemplate.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;


@Getter
@Setter
public class Student {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    private Integer age;

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student() {

    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name,
                   String email,
                   LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", email='" + email + "'" +
                ", dob='" + dob + "}";
    }
}
